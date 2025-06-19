package com.example.pocketdex.ui.mock_data

import java.util.UUID

/**
 * Data Class for UserCollection (All Cards owned by User):
 * contains the userId (UUID) of the user who owns the collection
 * and a list of UserCard Instances
 */
data class UserCollection(
    val userId: UUID,
    val userCards: List<CardWithUserData>
)
