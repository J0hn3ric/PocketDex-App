package com.example.pocketdex.ui.add_cards

import android.system.Os.remove
import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pocketdex.data.user_card.UserCard
import com.example.pocketdex.data.user_card.UserCardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditCollectionViewModel(
    private val userCardRepository: UserCardRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    val mode: EditCollectionType = savedStateHandle.get<String>("mode")
        ?.let { EditCollectionType.valueOf(it) }
        ?: EditCollectionType.ADD_CARDS
    private val _uiState = MutableStateFlow(EditCollectionUiState(mode))
    val uiState: StateFlow<EditCollectionUiState> = _uiState.asStateFlow()

    init {
        if (_uiState.value.mode == EditCollectionType.EDIT_COLLECTION) {
            viewModelScope.launch {
                userCardRepository.getIdQuantityMapping()
                    .collect { idQuantityMap ->
                        _uiState.update { currentState ->
                            currentState.copy(
                                updatedCardIdToQuantityMap = idQuantityMap,
                                originalCardIdToQuantityMap = idQuantityMap
                            )
                        }
                    }
            }
        }
    }

    fun onClickFilterButton() {
        _uiState.update { currentState ->
            currentState.copy(isFilterDialogOpened = true)
        }
    }

    fun onCloseFilterDialog() {
        _uiState.update { currentState ->
            currentState.copy(isFilterDialogOpened = false)
        }
    }

    fun onClickAddButton(cardId: String) {
        val newIdQuantityMap = uiState.value
            .updatedCardIdToQuantityMap
            .toMutableMap()

        val currentQuantity = newIdQuantityMap[cardId] ?: 0
        val updatedQuantity = currentQuantity + 1
        newIdQuantityMap[cardId] = updatedQuantity

        _uiState.update { currentState ->
            currentState.copy(updatedCardIdToQuantityMap = newIdQuantityMap)
        }


    }

    fun onClickRemoveButton(cardId: String) {
        val newIdQuantityMap = uiState.value
            .updatedCardIdToQuantityMap
            .toMutableMap()

        newIdQuantityMap[cardId] = newIdQuantityMap[cardId]!!.minus(1)

        if (newIdQuantityMap[cardId] == 0) {
            newIdQuantityMap.remove(cardId)
        }

        _uiState.update { currentState ->
            currentState.copy(updatedCardIdToQuantityMap = newIdQuantityMap)
        }
    }

    fun onAmountValueChange(newValue: String, cardId: String) {
        if (newValue.isDigitsOnly()) {
            val newValueNumerical = if (newValue == "") { 0 } else { newValue.toInt() }

            val newIdQuantityMap = uiState.value
                .updatedCardIdToQuantityMap
                .toMutableMap()

            if (newValueNumerical == 0) {
                newIdQuantityMap.remove(cardId)
            } else {
                newIdQuantityMap[cardId] = newValueNumerical
            }

            _uiState.update { currentState ->
                currentState.copy(updatedCardIdToQuantityMap = newIdQuantityMap)
            }
        }
    }

    fun onClickDoneButtonEditCollectionMode() {
        val cardList = uiState.value.cardList

        val userCardsToUpsert: MutableList<UserCard> = mutableListOf()
        val userCardsToDelete: MutableList<UserCard> = mutableListOf()

        val originalCardIdQuantityMap = uiState.value.originalCardIdToQuantityMap
        val updatedCardIdQuantityMap = uiState.value.updatedCardIdToQuantityMap

        originalCardIdQuantityMap.forEach { (cardId, _) ->
            if (!updatedCardIdQuantityMap.containsKey(cardId)) {
                userCardsToDelete.add(
                    UserCard(
                        cardInfo = cardList.find { it.id == cardId }!!,
                        quantity = 0,
                        isTradable = false
                    )
                )
            }
        }

        updatedCardIdQuantityMap.forEach { (cardId, quantity) ->
            userCardsToUpsert.add(
                UserCard(
                    cardInfo = cardList.find { it.id == cardId }!!,
                    quantity = quantity,
                    isTradable = quantity >= 2
                )
            )
        }

        viewModelScope.launch {
            userCardRepository.syncUserCards(
                userCardsToUpsert = userCardsToUpsert.toList(),
                userCardsToDelete = userCardsToDelete.toList()
            )
        }
    }

    fun onClickDoneButtonAddCardsMode() {
        // TODO: call the endpoint
    }


}