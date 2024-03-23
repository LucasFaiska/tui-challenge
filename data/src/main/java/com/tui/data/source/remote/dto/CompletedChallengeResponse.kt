package com.tui.data.source.remote.dto

import java.util.Date

data class CompletedChallengeResponse(
    val id: String,
    val name: String,
    val slug: String,
    val completedAt: Date,
    val completedLanguages: List<String>
)