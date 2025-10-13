package com.example.pocketdex.data.user_card

import android.util.Log
import com.example.pocketdex.data.user_card.utils.toEntity
import com.example.pocketdex.data.user_card.utils.toUserCard
import com.example.pocketdex.network.user_card.BackendUserCardApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserCardsRepository(
    private val userCardDao: UserCardDao,
    private val userCardApiService: BackendUserCardApiService
): UserCardRepository {

    override suspend fun syncUserCards(
        userCardsToUpsert: List<UserCard>,
        userCardsToDelete: List<UserCard>
    ) = userCardDao.applyDelta(
        upserts = userCardsToUpsert.map { it.toEntity() },
        deletes = userCardsToDelete.map { it.toEntity() }
    )

    override suspend fun updateUserCards(userCardsToUpdate: List<UserCard>) {

    }

    override fun getAllUserCardsStream(): Flow<List<UserCard>> = userCardDao.getAllCards()
        .map { entities -> entities.map { it.toUserCard() } }

    override fun getCardsWithFilterStream(
        name: String?,
        rarity: String?,
        expansion: String?,
        pack: String?,
        onlyTradable: Boolean
    ): Flow<List<UserCard>> = userCardDao
        .getCardsWithFilter(
            name = name,
            rarity = rarity,
            expansion = expansion,
            pack = pack,
            onlyTradable = onlyTradable
        ).map { entities ->
            Log.d("UserCardRepository", "entity: ${entities.toString()}")
            entities.map { it.toUserCard() }
        }

    override fun getIdQuantityMapping(): Flow<Map<String, Int>> =userCardDao.getIdQuantity()
        .map { idQuantities -> idQuantities.associate { it.id to it.quantity } }

}