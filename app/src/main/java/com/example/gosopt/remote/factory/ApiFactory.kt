package com.example.gosopt.remote.factory

import com.example.gosopt.remote.api.GitHubApi
import com.example.gosopt.remote.service.GitHubService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object ApiFactory {
    private const val Github_BASE_URL = "https://api.github.com/"

    fun createApi(): GitHubApi {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(Github_BASE_URL)
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
            .build()
            .create(GitHubApi::class.java)
    }

    fun createService(): GitHubService {
        return GitHubService(createApi())
    }
}