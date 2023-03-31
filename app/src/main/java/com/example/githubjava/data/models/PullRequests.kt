package com.example.githubjava.data.models

import com.google.gson.annotations.SerializedName

data class PullRequests (
    @SerializedName("login")
    val nomeAutorPullrequests: String?,
    @SerializedName("title")
    val tituloPullRequests: String?,
    @SerializedName("created_at")
    val dataPullRequests: String?,
    @SerializedName("body")
    val bodyPullRequest: String?,

    @SerializedName("user")
    val user: User?

    )

data class User(

    @SerializedName("login")
    val login: String?,

)
