package com.tui.data.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.tui.data.source.remote.CodewarsApi
import com.tui.data.source.remote.core.NetworkResponseAdapterFactory
import junit.framework.TestCase.assertEquals
import okhttp3.OkHttpClient
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModuleTest {

    @Test
    fun `Given a NetworkModule, When provideGson, Then returns the expected gson setup`() {
        val expectedGson = GsonBuilder()
            .setLenient()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()

        val providedGson = NetworkModule.provideGson()

        assertEquals(expectedGson.fieldNamingStrategy(), providedGson.fieldNamingStrategy())
    }

    @Test
    fun `Given a NetworkModule, When provideHttpClient, Then returns the expected OkHttpClient setup`() {
        val expectedOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(NetworkModule.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NetworkModule.TIMEOUT, TimeUnit.SECONDS)
            .build()

        val providedHttpClient = NetworkModule.provideHttpClient()

        assertEquals(expectedOkHttpClient.connectTimeoutMillis, providedHttpClient.connectTimeoutMillis)
        assertEquals(expectedOkHttpClient.readTimeoutMillis, providedHttpClient.readTimeoutMillis)
    }

    @Test
    fun `Given a NetworkModule, When provideRetrofitInstance, Then returns the expected Retrofit setup`() {
        val expectedRetrofit = Retrofit.Builder().client(NetworkModule.provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create(NetworkModule.provideGson()))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .baseUrl(NetworkModule.BASE_URL)
            .build()

        val providedRetrofit = NetworkModule.provideRetrofitInstance(
            NetworkModule.provideHttpClient(),
            NetworkModule.provideGson()
        )

        assertEquals(expectedRetrofit.baseUrl(), providedRetrofit.baseUrl())
        assertEquals(
            expectedRetrofit.callAdapterFactories()::class.java,
            providedRetrofit.callAdapterFactories()::class.java
        )
        assertEquals(
            expectedRetrofit.converterFactories()::class.java,
            providedRetrofit.converterFactories()::class.java
        )
    }

    @Test
    fun `Given a NetworkModule, When provideCodewarsApi, Then returns the expected CodewarsApi setup`() {
        val expectedCodewarsApi = NetworkModule.provideRetrofitInstance(
            NetworkModule.provideHttpClient(),
            NetworkModule.provideGson()
        ).create(CodewarsApi::class.java)

        val providedCodewarsApi = NetworkModule.provideCodewarsApi(
            NetworkModule.provideRetrofitInstance(
                NetworkModule.provideHttpClient(),
                NetworkModule.provideGson()
            )
        )

        assertEquals(expectedCodewarsApi::class.java, providedCodewarsApi::class.java)
    }

}