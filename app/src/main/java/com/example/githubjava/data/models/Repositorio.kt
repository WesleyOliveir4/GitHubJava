package com.example.githubjava.data.models

import com.google.gson.annotations.SerializedName

data class Repositorio(
    @SerializedName("name")
    val nomeRepositorio: String,
    @SerializedName("description")
    val descricaoRepositorio: String,
    @SerializedName("login")
    val nomeAutor: String,
    @SerializedName("avatar_url")
    val fotoAutor: String? = null,
    @SerializedName("stargazers_count")
    val numeroStars: String,
    @SerializedName("forks_count")
    val numeroForks: String,

    @SerializedName("owner")
    val owner: OwnerModel?
)

data class JavaRepos(

    @SerializedName("total_count")
    val totalCont: Long,
    @SerializedName("items")
    val items: MutableList<Repositorio>,

    )

data class OwnerModel(

    @SerializedName("login")
    val login: String?,

    @SerializedName("avatar_url")
    val avatarUrl: String?

)
