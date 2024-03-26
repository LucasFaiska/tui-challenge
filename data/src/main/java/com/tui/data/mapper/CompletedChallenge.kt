package com.tui.data.mapper

import com.tui.data.extension.toFormattedString
import com.tui.data.source.remote.dto.CompletedChallengeResponse
import com.tui.domain.model.CompletedChallenge

fun CompletedChallengeResponse.toCompletedChallenge() = CompletedChallenge(
    id = id,
    name = name,
    slug = slug,
    completedAt = completedAt.toFormattedString(),
    completedLanguages = completedLanguages
)