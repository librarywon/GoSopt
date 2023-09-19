package com.example.gosopt.ui.main.git

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gosopt.data.model.GitItem
import com.example.gosopt.data.model.GitItem.Repo

class GitViewModel : ViewModel() {
    val repoList = MutableLiveData<List<GitItem>>(
        listOf(
            GitItem.Title("Jaewon Seo"),
            Repo(
                1,
                "https://avatars.githubusercontent.com/u/66426967?v=4",
                "Pophory",
                "librarywon"
            ),
            Repo(
                2,
                "https://avatars.githubusercontent.com/u/66426967?v=4",
                "Carrot",
                "librarywon"
            ),
            Repo(
                3,
                "https://avatars.githubusercontent.com/u/66426967?v=4",
                "Naver",
                "librarywon"
            ), Repo(
                4,

                "https://avatars.githubusercontent.com/u/66426967?v=4",
                "Naver",
                "librarywon"
            ), Repo(
                5,
                "https://avatars.githubusercontent.com/u/66426967?v=4",
                "Naver",
                "librarywon"
            ), Repo(
                6,
                "https://avatars.githubusercontent.com/u/66426967?v=4",
                "Naver",
                "librarywon"
            ), Repo(
                7,
                "https://avatars.githubusercontent.com/u/66426967?v=4",
                "Naver",
                "librarywon"
            ),
            GitItem.Button("Delete")
        )
    )
}