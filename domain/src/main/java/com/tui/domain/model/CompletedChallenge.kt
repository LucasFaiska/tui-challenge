package com.tui.domain.model

import java.util.Date

data class CompletedChallenge(
    val id: String,
    val name: String?,
    val slug: String?,
    val completedAt: Date,
    val completedLanguages: List<String>?
)