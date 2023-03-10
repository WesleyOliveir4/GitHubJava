package com.example.githubjava.data.repository

import com.google.gson.JsonArray
import retrofit2.Call

interface PullRequestRepository {

    //Não vou utilizar no momento

    fun fetchCurrencies(criador: String, repositorio : String): Call<JsonArray>

}