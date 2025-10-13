package com.example.pocketdex.model

import android.util.Log
import kotlinx.serialization.Serializable
import okio.IOException

@Serializable
data class Response<T>(
    val body: T,
    val error: String? = ""
)

fun <T> Response<T>.getOrThrow(): T {
    if (error.isNullOrBlank()) {
        return body
    } else {
        Log.d("Response", "response error lol")
        throw IOException("Api error: $error")
    }
}