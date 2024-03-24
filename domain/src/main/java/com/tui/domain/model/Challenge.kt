package com.tui.domain.model

import java.util.Date

data class Challenge(
    val id: String?,
    val name: String?,
    val slug: String?,
    val url: String?,
    val category: String?,
    val description: String?,
    val tags: List<String>?,
    val languages: List<String>?,
    val rank: Rank?,
    val createdBy: User?,
    val approvedBy: User?,
    val totalAttempts: Int?,
    val totalCompleted: Int?,
    val totalStars: Int?,
    val voteScore: Int?,
    val publishedAt: Date?,
    val approvedAt: Date?
)