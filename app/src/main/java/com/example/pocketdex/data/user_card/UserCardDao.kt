package com.example.pocketdex.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface UserCardDao {

    @Upsert
    suspend fun updateUserCardCache(vararg userCard: UserCard)

    @Delete
    suspend fun deleteUserCards(userCards: List<UserCard>)

    @Query("SELECT * from user_cards")
    fun getAllCards(): Flow<List<UserCard>>

    @Query("""
        SELECT * from user_cards
        WHERE (:name IS NULL OR name LIKE ':name%')
        AND (:rarity IS NULL OR rarity = :rarity)
        AND (:expansion IS NULL OR expansion = :expansion)
        AND (:pack IS NULL OR pack = :pack)
        AND (:onlyTradable = 0 OR isTradable = 1)
    """)
    fun getCardsWithFilter(
        name: String?,
        rarity: String?,
        expansion: String?,
        pack: String?,
        onlyTradable: Boolean
    )

}