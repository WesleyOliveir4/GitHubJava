package com.example.githubjava.data.repositories

import com.example.githubjava.api.EndpointPullRequest

class PullRequestRepositoryImpl(
    private val endpointPullRequest: EndpointPullRequest
) : PullRequestRepository{

    override fun fetchCurrencies(): List<Any> {
        return endpointPullRequest.getCurrencies()
    }

}