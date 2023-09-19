package com.example.gosopt.ui.main.git

import androidx.lifecycle.ViewModel
import com.example.gosopt.data.model.GitItem
import com.example.gosopt.data.model.GitItem.Repo

class GitViewModel : ViewModel() {
    val repoList = listOf<GitItem>(
        GitItem.Title("Jaewon Seo"),
        Repo(
            "https://avatars.githubusercontent.com/u/66426967?v=4",
            "Pophory",
            "librarywon"
        ),
        Repo(
            "https://avatars.githubusercontent.com/u/66426967?v=4",
            "Carrot",
            "librarywon"
        ),
        Repo(
            "https://avatars.githubusercontent.com/u/66426967?v=4",
            "Naver",
            "librarywon"
        ), Repo(
            "https://avatars.githubusercontent.com/u/66426967?v=4",
            "Naver",
            "librarywon"
        ), Repo(
            "https://avatars.githubusercontent.com/u/66426967?v=4",
            "Naver",
            "librarywon"
        ), Repo(
            "https://avatars.githubusercontent.com/u/66426967?v=4",
            "Naver",
            "librarywon"
        ), Repo(
            "https://avatars.githubusercontent.com/u/66426967?v=4",
            "Naver",
            "librarywon"
        ), Repo(
            "https://avatars.githubusercontent.com/u/66426967?v=4",
            "Naver",
            "librarywon"
        ), Repo(
            "https://avatars.githubusercontent.com/u/66426967?v=4",
            "Naver",
            "librarywon"
        ), Repo(
            "https://avatars.githubusercontent.com/u/66426967?v=4",
            "Naver",
            "librarywon"
        ), Repo(
            "https://avatars.githubusercontent.com/u/66426967?v=4",
            "Naver",
            "librarywon"
        ), Repo(
            "https://avatars.githubusercontent.com/u/66426967?v=4",
            "Naver",
            "librarywon"
        )
    )
}