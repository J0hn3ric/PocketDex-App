package com.example.pocketdex.ui.mock_data

import androidx.annotation.DrawableRes

data class MockUserCards(
    val id: String,
    @DrawableRes val imgRes: Int,
    val quantity: Int
)
