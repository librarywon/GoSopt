package com.example.gosopt.remote.model

import com.example.gosopt.data.model.Repo
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRepo(
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: Owner,
) {
    fun toLocalModel(): Repo {
        return Repo(
            imageUrl = this.owner.avatarUrl,
            name = this.name,
            author = this.owner.login
        )
    }
}

@Serializable
data class Owner(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("login")
    val login: String,
)

