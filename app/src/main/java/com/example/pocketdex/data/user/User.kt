package com.example.pocketdex.data

data class User(
    val username: String,
    val friendId: String,
    val profileIcon: Int,
    val accessToken: String? = ""
)