package com.example.githubjava.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjava.data.api.EndpointRepositorios
import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.dao.RepositorioDao
import com.example.githubjava.data.model.Repositorio
import com.example.githubjava.presentation.home.HomeActivity
import com.example.githubjava.presentation.pullRequest.PullRequestActivity
import com.example.githubjava.presentation.state.HomeState
import com.example.githubjava.ui.adapter.ListaRepositoriosAdapter
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    @SuppressLint("StaticFieldLeak") private val context: Context,
    private val adapter: ListaRepositoriosAdapter,
    private val dao: RepositorioDao,
    @SuppressLint("StaticFieldLeak") private val homeActivity: HomeActivity,
) : ViewModel(), ListaRepositoriosAdapter.SelecionaRepositorio {

    private val _state by lazy { MutableLiveData<HomeState>() }
    val state: LiveData<HomeState> = _state
    private var paginaAtual: Int = 1

    fun configuraRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
    }

    fun configuraPaginacao(btnAnterior: Button, btnSeguinte: Button, tvNumeroPagina: TextView) {

        tvNumeroPagina.text = paginaAtual.toString()

        btnAnterior.setOnClickListener(View.OnClickListener {
            if (paginaAtual > 1) {
                paginaAtual -= 1
                tvNumeroPagina.text = paginaAtual.toString()
                consultaApiGit()
                dao.removeTodosRepositorios()
                adapter.atualiza(dao.buscaTodosRepositorios())
            } else {
                return@OnClickListener
            }
        })

        btnSeguinte.setOnClickListener(View.OnClickListener {
            paginaAtual += 1
            tvNumeroPagina.text = paginaAtual.toString()
            consultaApiGit()
            dao.removeTodosRepositorios()
            adapter.atualiza(dao.buscaTodosRepositorios())
        })
    }

    fun consultaApiGit() {
        buscandoRepositorios(paginaAtual)
        adapter.atualiza(dao.buscaTodosRepositorios())
    }

    fun buscandoRepositorios(page: Int) {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com/search/")
        val endpoint = retrofitClient.create(EndpointRepositorios::class.java)
        val page = page
        if (page < 1) {
            page == 1
        }

        endpoint.getCurrencies("$page").enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                var i: Int = 1

                val objeto = response.body()?.get("items")
                try {
                    objeto?.asJsonArray?.forEach {
                        val getOwners = objeto?.asJsonArray?.get(i)
                        val getOwner = getOwners?.asJsonObject?.get("owner")
                        val getItems = objeto?.asJsonArray?.get(i)
                        getItems?.asJsonObject?.get("name")

                         addRepositorioNovo(
                            nomeRepositorio = formataString(
                                getItems?.asJsonObject?.get("name").toString()
                            ),
                            descricaoRepositorio = formataString(
                                getItems?.asJsonObject?.get("description").toString()
                            ),
                            nomeAutor = formataString(
                                getOwner?.asJsonObject?.get("login").toString()
                            ),
                            fotoAutor = formataString(
                                getOwner?.asJsonObject?.get("avatar_url").toString()
                            ),
                            numeroStars = formataString(
                                getItems?.asJsonObject?.get("stargazers_count").toString()
                            ),
                            numeroForks = formataString(
                                getItems?.asJsonObject?.get("forks").toString()
                            )
                        )
                        if (i == 29) {
                            return
                        }
                        i++

                    }
                } catch (e: Exception) {
                    Log.d("Erro ao pesquisar repo", i.toString() + e.toString())
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

            }

        })


    }

    fun addRepositorioNovo(
        nomeRepositorio: String,
        descricaoRepositorio: String,
        nomeAutor: String,
        fotoAutor: String,
        numeroStars: String,
        numeroForks: String
    ) {

        val repositoroNovo = Repositorio(
            nomeRepositorio = nomeRepositorio,
            descricaoRepositorio = descricaoRepositorio,
            nomeAutor = nomeAutor,
            fotoAutor = fotoAutor,
            numeroStars = numeroStars,
            numeroForks = numeroForks
        )
        dao.adicionaRepositorio(repositoroNovo)
        adapter.atualiza(dao.buscaTodosRepositorios())
    }

    fun formataString(text: String): String {
        var textModified = text.substring(1, text.length - 1)
        return textModified
    }

    fun testeAplication(homeActivity: HomeActivity): Context {
    return
    }

    override fun selecionaRepositorio(repositorio: Repositorio) {
        val intent = Intent(homeActivity, PullRequestActivity::class.java).apply {
            putExtra("repositorio.nomeAutor", repositorio.nomeAutor)
            putExtra("repositorio.nomeRepositorio", repositorio.nomeRepositorio)
        }
        startActivity(intent)
    }
}