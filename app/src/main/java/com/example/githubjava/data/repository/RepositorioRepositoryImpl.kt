package com.example.githubjava.data.repository

import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.models.Repositorio
import com.example.githubjava.data.request.EndpointRepositorios
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class RepositorioRepositoryImpl() :RepositorioRepository {

    override suspend fun fetchCurrencies(page: String): MutableList<Repositorio> {
        return withContext(Dispatchers.IO){
            val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com/search/")
            val endpoint = retrofitClient.create(EndpointRepositorios::class.java)
            endpoint.getCurrencies(page).items
        }

    }

}