package com.example.pocketdex.ui.utils

import kotlinx.coroutines.flow.MutableStateFlow

inline fun <reified S : T, T : Any> MutableStateFlow<T>.updateIfSuccess(
    crossinline transform: S.() -> S
) {
    val current = value
    if (current is S) {
        value = current.transform()
    }
}