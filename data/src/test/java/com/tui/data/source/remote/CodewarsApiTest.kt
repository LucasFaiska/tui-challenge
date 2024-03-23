package com.tui.data.source.remote

import com.tui.data.source.remote.core.NetworkResponse
import com.tui.data.source.remote.core.NetworkResponseAdapterFactory
import com.tui.data.utils.Mocks.Companion.challengeResponseMock
import com.tui.data.utils.Mocks.Companion.completedChallengesResponseMock
import com.tui.data.utils.Utils
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CodewarsApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var api: CodewarsApi

    // This list of error codes is defined in https://dev.codewars.com/#errors
    private val codeWarsApiErrorCodes = listOf(
        400, // Bad Request
        401, // Unauthorized
        403, // Forbidden
        404, // Not Found
        405, // Method Not Allowed
        406, // Not Acceptable
        422, // Unprocessable Entity
        429, // Too Many Requests
        500, // Internal Server Error
        503  // Service Unavailable
    )

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()

        api = retrofit.create(CodewarsApi::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Given there is a success response with a correct body, When perform a request to challenges completed endpoint, Then returns the correct object`() = runBlocking {
        enqueueResponse(Utils.readFile("challenges_completed.json"), 200)
        val expectedResponse = completedChallengesResponseMock
        val result = api.getCompletedChallenges("foo", 0)
        val request = mockWebServer.takeRequest()
        assertEquals("/users/foo/code-challenges/completed?page=0", request.path)
        assertEquals(NetworkResponse.Success(expectedResponse), result)
    }

    @Test
    fun `Given there is a success response with a empty body, When perform a request to challenges completed endpoint, Then returns an error`() = runBlocking {
        enqueueResponse("", 200)
        val result = api.getCompletedChallenges("foo", 0)
        val request = mockWebServer.takeRequest()
        assertEquals("/users/foo/code-challenges/completed?page=0", request.path)
        assertEquals(NetworkResponse.Error, result)
    }

    @Test
    fun `Given there is a error response with error codes provided by Code Wars Api, When perform a request to challenges completed endpoint, Then returns an error`() = runBlocking {
        codeWarsApiErrorCodes.forEach { code ->
            enqueueResponse("", code)
            val result = api.getCompletedChallenges("foo", 0)
            val request = mockWebServer.takeRequest()
            assertEquals("/users/foo/code-challenges/completed?page=0", request.path)
            assertEquals(NetworkResponse.Error, result)
        }
    }

    @Test
    fun `Given there is a success response with a correct body, When perform a request to challenge details endpoint, Then returns the correct object`() = runBlocking {
        enqueueResponse(Utils.readFile("challenge_details.json"), 200)
        val expectedResponse = challengeResponseMock
        val result = api.getChallengeDetails("123")
        val request = mockWebServer.takeRequest()
        assertEquals("/code-challenges/123", request.path)
        assertEquals(NetworkResponse.Success(expectedResponse), result)
    }

    @Test
    fun `Given there is a success response with a empty body, When perform a request to challenge details endpoint, Then returns an error`() = runBlocking {
        enqueueResponse("", 200)
        val result = api.getChallengeDetails("123")
        val request = mockWebServer.takeRequest()
        assertEquals("/code-challenges/123", request.path)
        assertEquals(NetworkResponse.Error, result)
    }

    @Test
    fun `Given there is a error response with error codes provided by Code Wars Api, When perform a request to challenge details endpoint, Then returns an error`() = runBlocking {
        codeWarsApiErrorCodes.forEach { code ->
            enqueueResponse("", code)
            val result = api.getChallengeDetails("123")
            val request = mockWebServer.takeRequest()
            assertEquals("/code-challenges/123", request.path)
            assertEquals(NetworkResponse.Error, result)
        }
    }

    private fun enqueueResponse(body: String, code: Int) {
        val response = MockResponse()
            .setBody(body)
            .setResponseCode(code)

        mockWebServer.enqueue(response)
    }
}