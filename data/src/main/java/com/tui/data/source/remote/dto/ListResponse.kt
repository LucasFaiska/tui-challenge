package com.tui.data.source.remote.dto

data class ListResponse<T>(
    val totalPages: Int,
    val totalItems: Int,
    val data: List<T>
)