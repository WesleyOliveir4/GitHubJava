package com.example.githubjava.api.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtilsPull {
    companion object{
        fun getRetrofitPullInstance(pathInit: String,pathFinal: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(pathInit)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}