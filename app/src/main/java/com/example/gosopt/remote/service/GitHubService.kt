package com.example.gosopt.remote.service

import android.content.ContentValues.TAG
import android.util.Log
import com.example.gosopt.data.model.Repo as LocalRepo
import com.example.gosopt.remote.api.GitHubApi

class GitHubService(private val api: GitHubApi) {
    suspend fun fetchRepositories(username: String): List<LocalRepo> {
        val response = api.getUserRepositories(username)

        // 로그에 응답 본문과 오류 본문 추가
        Log.e(TAG, "fetchRepositories Response: $response")
        Log.e(TAG, "fetchRepositories Response Body: ${response.body()}")
        Log.e(TAG, "fetchRepositories Error Body: ${response.errorBody()?.string()}")

        if (response.isSuccessful) {
            return response.body()?.map { it.toLocalModel() } ?: emptyList()
        } else {
            // 오류 본문을 예외 메시지에 포함
            throw Exception("API error: ${response.code()} - ${response.errorBody()?.string()}")
        }
    }
}
