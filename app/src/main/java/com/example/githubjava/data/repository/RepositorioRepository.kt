package com.example.githubjava.data.repository

import com.example.githubjava.data.models.JavaRepos
import com.example.githubjava.data.models.Repositorio
import com.google.gson.JsonObject
import retrofit2.Call

interface RepositorioRepository {

    suspend fun fetchCurrencies(page: String): MutableList<Repositorio>

}