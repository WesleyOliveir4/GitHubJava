package com.example.githubjava.model

import java.math.BigDecimal

data class Repositorio (

    val nomeRepositorio: String,
    val descricaoRepositorio: String,
    val nomeAutor: String,
    val fotoAutor: String? = null,
    val numeroStars: Int,
    val numeroForks: Int,
    )
