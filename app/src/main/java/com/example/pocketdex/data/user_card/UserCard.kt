package com.example.pocketdex.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_cards")
data class UserCard(
    @PrimaryKey val id: String,
    val name: String,
    val quantity: Int,
    val isTradable: Boolean,
    val imgRes: String, // store url or cached path, coil will do the rest
    val expansion: String,
    val pack: String,
    val rarity: String
)
