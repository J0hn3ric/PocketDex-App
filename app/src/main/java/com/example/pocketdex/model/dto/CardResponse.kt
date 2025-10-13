package com.example.pocketdex.model

import com.example.pocketdex.data.card.Card
import com.example.pocketdex.data.user_card.utils.toExpansion
import com.example.pocketdex.data.user_card.utils.toPack
import com.example.pocketdex.data.user_card.utils.toRarity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardResponse(
    val id: String,
    @SerialName("card_name")
    val name: String,
    @SerialName(value = "card_img")
    val cardImg: String,
    @SerialName("card_rarity")
    val rarity: String,
    @SerialName("card_pack")
    val packId: String,
    @SerialName("expansion")
    val expansionId: String
)

fun CardResponse.toCard(): Card = Card(
    id = id,
    imgResUrl = cardImg,
    name = name,
    rarity = rarity.toRarity(),
    pack = packId.toPack(),
    expansion = expansionId.toExpansion()
)
