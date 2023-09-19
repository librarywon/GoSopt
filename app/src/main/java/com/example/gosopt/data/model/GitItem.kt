package com.example.gosopt.data.model

sealed class GitItem {
    data class Title(val text: String) : GitItem()
    data class Repo(val id: Long, val imageUrl: String, val name: String, val author: String) : GitItem()
    data class Button(val text: String) : GitItem()
}

