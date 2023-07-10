package com.example.githubjava.data.request

import com.example.githubjava.domain.models.JavaRepos
import com.example.githubjava.domain.models.Repositorio
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndpointRepositorios {
    @GET("https://api.github.com/search/repositories?q=language:Java&sort=stars&")
    suspend fun getCurrencies(@Query("page=1") numberPage: String) : JavaRepos

}