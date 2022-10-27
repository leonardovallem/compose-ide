package com.vallem.composeide.ui

sealed class ScreenState {
    data class Success<T>(val data: T) : ScreenState()
    data class Failure<T>(val error: T) : ScreenState()
    data class ShowSnackbar(val message: String) : ScreenState()
    object Loading : ScreenState()
}

fun <T> ScreenState.onSuccess(action: (T) -> Unit) = apply {
    if (this is ScreenState.Success<*>) action(this.data as T)
}

fun <T> ScreenState.onFailure(action: (T) -> Unit) = apply {
    if (this is ScreenState.Failure<*>) action(this.error as T)
}

