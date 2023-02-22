package com.example.githubjava.data.mapper

import com.example.githubjava.data.request.EndpointRepositorios
import retrofit2.Retrofit

class ResponseRepositorios(
    val retrofit: Retrofit
) {
    fun response(): Any{
        return retrofit.create(EndpointRepositorios::class.java)
    }
}