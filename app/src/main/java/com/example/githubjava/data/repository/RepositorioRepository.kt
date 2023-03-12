package com.example.githubjava.data.repository

import com.google.gson.JsonObject
import retrofit2.Call

interface RepositorioRepository {

    fun fetchCurrencies(page: String): Call<JsonObject>

}