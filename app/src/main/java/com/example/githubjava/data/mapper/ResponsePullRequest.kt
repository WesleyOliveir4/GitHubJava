package com.example.githubjava.data.mapper

import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.models.PullRequests
import com.example.githubjava.data.request.EndpointPullRequest
import retrofit2.Retrofit

class ResponsePullRequest() {

    private val listaPullRequest: MutableList<PullRequests> = mutableListOf()

    fun addPullRequest(
        nomeAutor: String,
        nomeTitulo: String,
        dataPull: String,
        bodyPull: String

    ) {

        val pullRequestNovo = PullRequests(
            nomeAutorPullrequests = nomeAutor,
            tituloPullRequests = nomeTitulo,
            dataPullRequests = dataPull,
            bodyPullRequest = bodyPull,
        )
        listaPullRequest.add(pullRequestNovo)
//        dao.adicionaPullRequest(pullRequestNovo)
//        adapter.atualiza(dao.buscaTodosPullRequests())
    }

    fun fetchListPullRequest(): MutableList<PullRequests>{
        return listaPullRequest
    }

}