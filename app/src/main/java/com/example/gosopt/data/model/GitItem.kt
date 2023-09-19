package com.example.gosopt.data.model

sealed class GitItem {
    data class Title(val text: String) : GitItem()
    data class Repo(val imageUrl: String, val name: String, val author: String) : GitItem()
}

