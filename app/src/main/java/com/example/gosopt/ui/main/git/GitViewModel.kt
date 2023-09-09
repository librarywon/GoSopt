package com.example.gosopt.ui.main.git

import androidx.lifecycle.ViewModel
import com.example.gosopt.data.model.Repo

class GitViewModel : ViewModel() {
    val repoList = listOf<Repo>(
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
        )
    )
}