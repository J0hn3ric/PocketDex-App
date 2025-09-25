package com.example.pocketdex.data.user_card

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_cards")
data class UserCardEntity(
    @PrimaryKey val id: String,
    val name: String,
    val quantity: Int,
    val isTradable: Boolean,
    val imgResUrl: String, // store url or cached path, coil will do the rest
    val expansion: String,
    val pack: String,
    val rarity: String
)
