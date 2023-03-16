package com.example.githubjava.data.repository

import com.example.githubjava.data.models.PullRequests
import com.google.gson.JsonArray
import retrofit2.Call

interface PullRequestRepository {

    fun fetchCurrencies(criador: String, repositorio : String): MutableList<PullRequests>

}