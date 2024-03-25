package com.tui.data.mapper

import com.tui.data.source.remote.dto.ChallengeResponse
import com.tui.data.source.remote.dto.RankResponse
import com.tui.data.source.remote.dto.UserResponse
import com.tui.domain.model.Challenge
import com.tui.domain.model.Rank
import com.tui.domain.model.User

fun ChallengeResponse.toChallenge() = Challenge(
    id = id,
    name = name,
    slug = slug,
    url = url,
    category = category,
    description = description,
    tags = tags,
    languages = languages,
    rank = rank.toRank(),
    createdBy = createdBy.toUser(),
    approvedBy = approvedBy.toUser(),
    totalAttempts = totalAttempts,
    totalCompleted = totalCompleted,
    totalStars = totalStars,
    voteScore = voteScore,
    publishedAt = publishedAt,
    approvedAt = approvedAt
)

fun RankResponse.toRank() = Rank(
    id = id,
    name = name,
    color = color
)

fun UserResponse.toUser() = User(
    username = username,
    url = url
)