package com.example.pocketdex.ui.mock_data

import androidx.compose.ui.node.TouchBoundsExpansion
import java.util.UUID

/**
 * Data Class for CardWithUserData, a Card owned by User, with User Data (quantity owned):
 * contains the cardId (String) of the Card,
 * the name (String) of the Card,
 * the image url (String) of the Card,
 * the rarity (Rarity) of the Card,
 * the packId (String) of the Pack the Card comes from,
 * the expansionId (String) of the Expansion the Card cones from
 * and the quantity (Int) of the card in the user's possession.
 *
 * This Data Class will be used for User Data centric pages
 * (Collections page, Singular Card page, etc..)
 */
data class CardWithUserData(
    val id: String,
    val name: String,
    val imageUrl: String,
    val rarity: Rarity,
    val packId: String,
    val expansionId: String,

    val quantity: Int // UserCard data
)
