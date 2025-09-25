package com.example.pocketdex.ui.add_cards

import com.example.pocketdex.data.card.Card
import com.example.pocketdex.data.user_card.UserCard
import com.example.pocketdex.ui.components.Type
import com.example.pocketdex.ui.home.FilterState
import com.example.pocketdex.ui.mock_data.CardsDataSource

enum class EditCollectionType{
    EDIT_COLLECTION,
    ADD_CARDS
}

data class EditCollectionUiState(
    val mode: EditCollectionType = EditCollectionType.ADD_CARDS,
    val originalCardIdToQuantityMap: Map<String, Int> = HashMap<String, Int>(),
    val updatedCardIdToQuantityMap: Map<String, Int> = HashMap<String, Int>(),
    val isFilterDialogOpened: Boolean = false,
    val appliedFilter: FilterState = FilterState(),
    val cardList: List<Card> = CardsDataSource.mockCardsSource
)
