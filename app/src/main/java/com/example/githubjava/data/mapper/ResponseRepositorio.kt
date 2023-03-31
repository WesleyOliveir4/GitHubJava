package com.example.githubjava.data.mapper

import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.models.PullRequests
import com.example.githubjava.data.models.Repositorio
import com.example.githubjava.data.request.EndpointPullRequest
import retrofit2.Retrofit

class ResponseRepositorio() {

    private val listaRepositorios: MutableList<Repositorio> = mutableListOf()

    fun addRepositorio(
        nomeRepositorio: String,
        descricaoRepositorio: String,
        nomeAutor: String,
        fotoAutor: String,
        numeroStars: String,
        numeroForks: String
    ) {

        val RepositorioNovo = Repositorio(
            nomeRepositorio = nomeRepositorio,
            descricaoRepositorio = descricaoRepositorio,
            nomeAutor = nomeAutor,
            fotoAutor = fotoAutor,
            numeroStars = numeroStars,
            numeroForks = numeroForks,
            owner = null
        )
        listaRepositorios.add(RepositorioNovo)
    }

    fun fetchListRepositorios(): MutableList<Repositorio>{
        return listaRepositorios
    }

    fun clearListRepositorios(){
        listaRepositorios.clear()
    }

}