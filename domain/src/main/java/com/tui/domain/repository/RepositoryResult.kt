package com.tui.domain.repository

sealed class RepositoryResult<out T> {

    data class Success<T>(val data: T) : RepositoryResult<T>()

    data class Error(val exception: Exception) : RepositoryResult<Nothing>()
}