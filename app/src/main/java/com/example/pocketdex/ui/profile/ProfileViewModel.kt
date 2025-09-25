package com.example.pocketdex.ui.user_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pocketdex.PocketDexApplication
import com.example.pocketdex.data.user.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking

class ProfileViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = combine(
        userPreferencesRepository.username,
        userPreferencesRepository.friendId,
        userPreferencesRepository.profileIcon
    ) { username, friendId, profileIcon ->
        ProfileUiState(
            username = username,
            friendId = friendId,
            profileIcon = profileIcon
        ) }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = ProfileUiState()
        )

}