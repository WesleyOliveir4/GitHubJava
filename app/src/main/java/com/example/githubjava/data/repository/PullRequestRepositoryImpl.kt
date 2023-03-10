package com.example.githubjava.data.repository

import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.models.PullRequests
import com.example.githubjava.data.request.EndpointPullRequest
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Retrofit

class PullRequestRepositoryImpl(
) : PullRequestRepository {


   //Não vou utilizar no momento

    override fun fetchCurrencies(criador: String, repositorio: String): Call<JsonArray> {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com/repos/")
        val endpoint = retrofitClient.create(EndpointPullRequest::class.java)
        return endpoint.getCurrencies(criador,repositorio)
    }

}