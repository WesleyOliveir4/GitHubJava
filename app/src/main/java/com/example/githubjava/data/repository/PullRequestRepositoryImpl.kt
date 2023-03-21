package com.example.githubjava.data.repository

import android.util.Log
import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.mapper.ResponsePullRequest
import com.example.githubjava.data.models.PullRequests
import com.example.githubjava.data.request.EndpointPullRequest
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PullRequestRepositoryImpl(
) : PullRequestRepository {


    override suspend fun fetchCurrencies(criador: String, repositorio: String): MutableList<PullRequests> {

        val responsePullRequest: ResponsePullRequest = ResponsePullRequest()
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com/repos/")
        val endpoint = retrofitClient.create(EndpointPullRequest::class.java)
       return endpoint.getCurrencies(criador, repositorio)

    }


    fun formataString(text: String): String {
        val textModified = text.substring(1, text.length - 1)
        return textModified
    }

    fun formataDataString(dataText: String): String {
        var textModified = dataText.substring(1, dataText.length - 1)
        textModified = textModified.substring(0,10)
        return textModified
    }

}