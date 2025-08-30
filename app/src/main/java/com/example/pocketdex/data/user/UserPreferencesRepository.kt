package com.example.pocketdex.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.pocketdex.R
import com.example.pocketdex.data.UserPreferencesRepository.Companion.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.lang.Exception

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val USERNAME = stringPreferencesKey("username")
        val FRIEND_ID = stringPreferencesKey("friend_id")
        val PROFILE_ICON = intPreferencesKey("profile_icon")
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        const val TAG = "UserPreferencesRepo"
    }

    suspend fun createNewUser(
        username: String,
        friendId: String,
        profileIcon: Int,
        accessToken: String = ""
    ) {
        dataStore.edit { preferences ->
            preferences[USERNAME] = username
            preferences[FRIEND_ID] = friendId
            preferences[PROFILE_ICON] = profileIcon
            preferences[ACCESS_TOKEN] = accessToken
        }
    }

    suspend fun editExistingUser(
        newUsername: String = "",
        newFriendId: String = "",
        newProfileIcon: Int? = null,
    ) {
        dataStore.edit { preferences ->
            if (newUsername != "") {
                preferences[USERNAME] = newUsername
            }

            if (newFriendId != "") {
                preferences[FRIEND_ID] = newFriendId
            }

            if (newProfileIcon != null) {
                preferences[PROFILE_ICON] = newProfileIcon
            }
        }
    }

    val username: Flow<String> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
        preferences[USERNAME] ?: ""
    }

    val friendId: Flow<String> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
        preferences[FRIEND_ID] ?: ""
    }

    val profileIcon: Flow<Int> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
        preferences[PROFILE_ICON] ?: R.drawable.baseline_person_24
    }

    val accessToken: Flow<String> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
        preferences[ACCESS_TOKEN] ?: ""
    }

}