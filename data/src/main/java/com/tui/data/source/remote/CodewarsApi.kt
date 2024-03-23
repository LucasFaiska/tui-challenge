package com.tui.data.source.remote

import com.tui.data.source.remote.core.NetworkResponse
import com.tui.data.source.remote.dto.ChallengeResponse
import com.tui.data.source.remote.dto.CompletedChallengeResponse
import com.tui.data.source.remote.dto.ListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CodewarsApi {

    @GET("users/{user}/code-challenges/completed")
    suspend fun getCompletedChallenges(
        @Path("user") user: String,
        @Query("page") page: Int
    ): NetworkResponse<ListResponse<CompletedChallengeResponse>>

    @GET("code-challenges/{id}")
    suspend fun getChallengeDetails(@Path("id") id: String): NetworkResponse<ChallengeResponse>

}