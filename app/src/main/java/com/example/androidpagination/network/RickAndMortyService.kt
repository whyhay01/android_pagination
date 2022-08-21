package com.example.androidpagination.network

import com.example.androidpagination.data.model.RickAndMortyResponse
import com.example.androidpagination.utils.API_CONNECT_TIMEOUT
import com.example.androidpagination.utils.API_READ_TIMEOUT
import com.example.androidpagination.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface RickAndMortyService {

    @GET("/character")
    suspend fun getCharacter(): RickAndMortyResponse
}

fun createApiService(): RickAndMortyService{
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(createHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(RickAndMortyService::class.java)
}

private fun createHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(createLoggingInterceptor())
        .connectTimeout(API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(API_READ_TIMEOUT, TimeUnit.SECONDS)
        .build()
}

private fun createLoggingInterceptor():HttpLoggingInterceptor{
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return interceptor
}