package com.example.githubjava.data.repository

import com.example.githubjava.BuildConfig
import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.domain.models.Repositorio
import com.example.githubjava.data.request.EndpointRepositorios
import com.example.githubjava.domain.repositorio.RepositorioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositorioRepositoryImpl() : RepositorioRepository {

    val RepositoriosPath = BuildConfig.RepositoriosPath

    override suspend fun fetchCurrencies(page: String): MutableList<Repositorio> {
        return withContext(Dispatchers.IO){
            val retrofitClient = NetworkUtils.getRetrofitInstance(RepositoriosPath)
            val endpoint = retrofitClient.create(EndpointRepositorios::class.java)
            endpoint.getCurrencies(page).items.toItemJava().toMutableList()

        }

    }

    private fun List<Repositorio>.toItemJava() = map {
        Repositorio(
            nomeRepositorio = it.nomeRepositorio ?: "",
            descricaoRepositorio = it.descricaoRepositorio?: "",
            nomeAutor =  it.owner?.login ?: "",
            fotoAutor = it.owner?.avatarUrl ?: "",
            numeroStars = it.numeroForks.toString(),
            numeroForks = it.numeroStars.toString(),
            owner = it.owner
        )
    }

}