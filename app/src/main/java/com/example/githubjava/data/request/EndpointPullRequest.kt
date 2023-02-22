package com.example.githubjava.data.request

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndpointPullRequest {
    @GET("https://api.github.com/repos/{criador}/{repositorio}/pulls")
    fun getCurrencies(
        @Path(value = "criador",encoded = true)criador :String,
        @Path(value = "repositorio",encoded = true) repositorio:String
    ) : Call<JsonArray>


}