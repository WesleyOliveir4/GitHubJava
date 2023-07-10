package com.example.githubjava.domain.repositorio

import com.example.githubjava.domain.models.JavaRepos
import com.example.githubjava.domain.models.Repositorio
import com.google.gson.JsonObject
import retrofit2.Call

interface RepositorioRepository {

    suspend fun fetchCurrencies(page: String): MutableList<Repositorio>

}