package com.example.githubjava.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjava.data.request.EndpointPullRequest
import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.dao.PullRequestDao
import com.example.githubjava.data.model.PullRequests
import com.example.githubjava.presentation.pullRequest.PullRequestActivity
import com.example.githubjava.ui.adapter.ListaPullRequestsAdapter
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestModel(pullRequestActivity: PullRequestActivity) : ViewModel() {

    private var dao = PullRequestDao()
    private var adapter =
        ListaPullRequestsAdapter(context = pullRequestActivity, pullRequests = dao.buscaTodosPullRequests())

    fun configuraRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
    }
    fun buscandoPullRequests(nomeCriador: String, nomeRepositorio: String) {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com/repos/")
        val endpoint = retrofitClient.create(EndpointPullRequest::class.java)
        dao.removeTodosPullRequests()
        adapter.atualiza(dao.buscaTodosPullRequests())

        endpoint.getCurrencies(nomeCriador,nomeRepositorio).enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                var i: Int = 1


                val objeto = response.body()?.asJsonArray

                try {
                    objeto?.asJsonArray?.forEach {
                        val getUsers = objeto?.asJsonArray?.get(i)
                        val getUser = getUsers?.asJsonObject?.get("user")

                        addPullRequest(
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


    }


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
        dao.adicionaPullRequest(pullRequestNovo)
        adapter.atualiza(dao.buscaTodosPullRequests())
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