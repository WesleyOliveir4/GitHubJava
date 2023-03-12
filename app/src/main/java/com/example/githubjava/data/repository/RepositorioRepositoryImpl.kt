package com.example.githubjava.data.repository

import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.request.EndpointRepositorios
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call

class RepositorioRepositoryImpl() :RepositorioRepository {

    override fun fetchCurrencies(page: String): Call<JsonObject> {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com/search/")
        val endpoint = retrofitClient.create(EndpointRepositorios::class.java)
        return endpoint.getCurrencies(page)
    }

}