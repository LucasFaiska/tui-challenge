package com.tui.data.source.remote.dto

import java.util.Date

data class ChallengeResponse(
    val id: String,
    val name: String,
    val slug: String,
    val url: String,
    val category: String,
    val description: String,
    val tags: List<String>,
    val languages: List<String>,
    val rank: RankResponse,
    val createdBy: UserResponse,
    val approvedBy: UserResponse,
    val totalAttempts: Int,
    val totalCompleted: Int,
    val totalStars: Int,
    val voteScore: Int,
    val publishedAt: Date,
    val approvedAt: Date
)