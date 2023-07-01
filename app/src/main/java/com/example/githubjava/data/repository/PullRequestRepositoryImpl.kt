package com.example.githubjava.data.repository

import android.util.Log
import com.example.githubjava.BuildConfig
import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.mapper.ResponsePullRequest
import com.example.githubjava.data.models.PullRequests
import com.example.githubjava.data.request.EndpointPullRequest
import com.google.gson.JsonArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PullRequestRepositoryImpl(
) : PullRequestRepository {

    val PullRequestsPath = BuildConfig.PullRequestsPath
    override suspend fun fetchCurrencies(criador: String, repositorio: String): MutableList<PullRequests> {
        return withContext(Dispatchers.IO) {
            val retrofitClient = NetworkUtils.getRetrofitInstance(PullRequestsPath)
            val endpoint = retrofitClient.create(EndpointPullRequest::class.java)
            endpoint.getCurrencies(criador, repositorio).toPullRequest().toMutableList()
        }
    }
    

    private fun formataDataString(dataText: String): String {
        var textModified = dataText.substring(0, dataText.length - 10)
        return textModified.let{ data ->
            val ano = data.substring(0,4)
            val dia = data.substring(8,10)
            val mes = data.substring(5,7)

            val dataBr = "$dia/$mes/$ano"
            dataBr
        }


    }

    private fun MutableList<PullRequests>.toPullRequest() = map {
        PullRequests(
           nomeAutorPullrequests = it.user?.login?: "",
           tituloPullRequests = it.tituloPullRequests?: "",
           dataPullRequests = formataDataString(it.dataPullRequests?: ""),
           bodyPullRequest = it.bodyPullRequest?: "",
           user = it.user
        )
    }


}