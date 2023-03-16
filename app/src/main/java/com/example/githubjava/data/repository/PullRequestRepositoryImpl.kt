package com.example.githubjava.data.repository

import android.util.Log
import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.mapper.ResponsePullRequest
import com.example.githubjava.data.models.PullRequests
import com.example.githubjava.data.request.EndpointPullRequest
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PullRequestRepositoryImpl(
) : PullRequestRepository {


    override fun fetchCurrencies(criador: String, repositorio: String): MutableList<PullRequests> {

        val responsePullRequest: ResponsePullRequest = ResponsePullRequest()
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com/repos/")
        val endpoint = retrofitClient.create(EndpointPullRequest::class.java)
        val currencies = endpoint.getCurrencies(criador, repositorio)


        currencies.enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                var i: Int = 1


                val objeto = response.body()?.asJsonArray

                try {
                    objeto?.asJsonArray?.forEach {
                        val getUsers = objeto?.asJsonArray?.get(i)
                        val getUser = getUsers?.asJsonObject?.get("user")

                        responsePullRequest.addPullRequest(
                            nomeAutor =formataString(getUser?.asJsonObject?.get("login").toString()) ,
                            nomeTitulo = formataString(getUsers?.asJsonObject?.get("title").toString()),
                            dataPull = formataDataString(getUsers?.asJsonObject?.get("created_at").toString()),
                            bodyPull = formataString(getUsers?.asJsonObject?.get("body").toString()),
                        )

                        i++
                    }

                } catch (e: Exception) {
                    Log.d("Erro ao pesquisar repo", i.toString() + e.toString())
                }
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.d("Erro ao pesquisar repo", t.toString())
            }

        })

        return responsePullRequest.fetchListPullRequest()

    }


    fun formataString(text: String): String {
        val textModified = text.substring(1, text.length - 1)
        return textModified
    }

    fun formataDataString(dataText: String): String {
        var textModified = dataText.substring(1, dataText.length - 1)
        textModified = textModified.substring(0,10)
        return textModified
    }

}