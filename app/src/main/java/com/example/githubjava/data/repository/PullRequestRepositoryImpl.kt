package com.example.githubjava.data.repository

import com.example.githubjava.data.request.EndpointPullRequest

class PullRequestRepositoryImpl(
    private val endpointPullRequest: EndpointPullRequest
) : PullRequestRepository {

   //Não vou utilizar no momento

    override fun fetchCurrencies(): List<Any> {
        return listOf(endpointPullRequest.getCurrencies("x","y"))
    }

}