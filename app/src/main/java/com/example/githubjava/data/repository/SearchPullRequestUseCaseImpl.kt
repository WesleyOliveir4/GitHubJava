package com.example.githubjava.data.repository

import com.example.githubjava.BuildConfig
import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.domain.models.PullRequests
import com.example.githubjava.data.request.EndpointPullRequest
import com.example.githubjava.domain.pullRequest.SearchPullRequestUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPullRequestUseCaseImpl(
) : SearchPullRequestUseCase {

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