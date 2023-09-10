package com.example.gosopt.ui.main.git

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gosopt.data.model.Repo
import com.example.gosopt.remote.factory.ApiFactory
import com.example.gosopt.remote.service.GitHubService
import kotlinx.coroutines.launch

class GitViewModel(private val gitHubService: GitHubService = ApiFactory.createService()) :
    ViewModel() {
    private val _repos = MutableLiveData<List<Repo>>()
    val repos: LiveData<List<Repo>> get() = _repos

    fun fetchRepos(user: String) {
        viewModelScope.launch {
            try {
                val fetchedRepos = gitHubService.fetchRepositories(user)
                _repos.postValue(fetchedRepos)
                Log.d("GitViewModel", "fetchedRepos: $fetchedRepos") // 로그 출력
            } catch (e: Exception) {
                // 에러 처리 (예: 로그 기록, 사용자에게 에러 메시지 표시 등)
                Log.e("GitViewModel", "fetchedRepos: $e") // 로그 출력
            }
        }
    }
}