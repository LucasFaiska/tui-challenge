package com.tui.data.source.remote.core

sealed class NetworkResponse<out T : Any> {
    data class Success<T : Any>(val body: T) : NetworkResponse<T>()
    data object Error : NetworkResponse<Nothing>()
}