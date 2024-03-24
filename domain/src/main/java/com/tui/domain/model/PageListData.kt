package com.tui.domain.model

data class PageListData<T>(
    val totalPages: Int,
    val totalItems: Int,
    val data: List<T>?
)