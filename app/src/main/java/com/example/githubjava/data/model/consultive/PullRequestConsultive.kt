package com.example.githubjava.data.model.consultive

import android.util.Log
import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.dao.PullRequestDao
import com.example.githubjava.data.models.PullRequests
import com.example.githubjava.data.models.Repositorio
import com.example.githubjava.data.repository.PullRequestRepositoryImpl
import com.example.githubjava.data.repository.RepositorioRepositoryImpl
import com.example.githubjava.data.request.EndpointPullRequest
import com.example.githubjava.ui.adapter.ListaPullRequestsAdapter
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestConsultive() {

    private val pullRequestRepositoryImpl: PullRequestRepositoryImpl = PullRequestRepositoryImpl()

    suspend fun consultaPullRequest(
        nomeCriador: String,
        nomeRepositorio: String
    ): MutableList<PullRequests> {
        return pullRequestRepositoryImpl.fetchCurrencies(nomeCriador, nomeRepositorio)
    }


}