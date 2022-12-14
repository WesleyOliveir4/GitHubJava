package com.example.githubjava.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.githubjava.api.EndpointPullRequest
import com.example.githubjava.api.network.NetworkUtils
import com.example.githubjava.dao.PullRequestDao
import com.example.githubjava.databinding.ActivityPullRequestBinding
import com.example.githubjava.model.PullRequests
import com.example.githubjava.ui.recyclerview.adapter.ListaPullRequestsAdapter
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPullRequestBinding.inflate(layoutInflater)
    }
    private var dao = PullRequestDao()
    private var adapter =
        ListaPullRequestsAdapter(context = this, pullRequests = dao.buscaTodosPullRequests())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        val nomeCriadorSelecionado = intent?.getStringExtra("repositorio.nomeAutor")
        val nomeRepositorioSelecionado = intent?.getStringExtra("repositorio.nomeRepositorio")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        dao.removeTodosPullRequests()
        configuraRecyclerView()
        buscandoRepositorios(nomeCriadorSelecionado.toString(),nomeRepositorioSelecionado.toString())
    }

    private fun configuraRecyclerView() {
        var recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
    }

    fun buscandoRepositorios(nomeCriador: String, nomeRepositorio: String) {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com/repos/")
        val endpoint = retrofitClient.create(EndpointPullRequest::class.java)

        endpoint.getCurrencies(nomeCriador,nomeRepositorio).enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                var i: Int = 1

                val objeto = response.body()?.asJsonArray

                Log.d("teste request", objeto.toString())
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
        var textModified = text.substring(1, text.length - 1)
        return textModified
    }
    fun formataDataString(dataText: String): String {
        var textModified = dataText.substring(1, dataText.length - 1)
        textModified = textModified.substring(0,10)
        return textModified
    }

}