package com.example.pocketdex.ui.mock_data

/**
 *  Data Class for Card:
 *  contains the cardId (String) of the Card,
 *  the name (String) of the Card,
 *  the image url (String) of the Card,
 *  the rarity (Rarity) of the Card,
 *  the packId (String) of the Pack the Card comes from,
 *  and the expansionId (String) of the Expansion the Card comes from.
 *
 *  this data class is used to store static Card data (even Cards the user doesn't own),
 *  used more for the window to add cards to collection.
 */
data class Card(
    val id: String,
    val name: String,
    val imageUrl: String,
    val rarity: Rarity,
    val packId: String,
    val expansionId: String
)
