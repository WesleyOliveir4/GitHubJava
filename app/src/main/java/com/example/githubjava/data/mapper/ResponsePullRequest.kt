package com.example.githubjava.data.mapper

import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.request.EndpointPullRequest
import retrofit2.Retrofit

class ResponsePullRequest(
    val retrofit: Retrofit
) {
    fun response(): Any{
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com/repos/")
        return retrofit.create(EndpointPullRequest::class.java)
    }
}