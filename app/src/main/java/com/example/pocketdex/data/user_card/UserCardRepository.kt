package com.example.pocketdex.data

import kotlinx.coroutines.flow.Flow

interface UserCardRepository {

    fun getAllUserCardsStream(): Flow<List<UserCard>>

    

}