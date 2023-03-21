package com.example.githubjava.data.models

import com.google.gson.annotations.SerializedName

data class Repositorio (
    @SerializedName("name")
    val nomeRepositorio: String,
//    val descricaoRepositorio: String,
//    val nomeAutor: String,
//    val fotoAutor: String? = null,
//    val numeroStars: String,
//    val numeroForks: String,
    )
data class JavaRepos(

    @SerializedName("total_count")
    val totalCont : Long,
    @SerializedName("items")
    val items : MutableList<Repositorio>
)