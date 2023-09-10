package com.example.gosopt.remote.api

import com.example.gosopt.remote.model.RemoteRepo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{username}/repos")
    suspend fun getUserRepositories(@Path("username") username: String): Response<List<RemoteRepo>>
}