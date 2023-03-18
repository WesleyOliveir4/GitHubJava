package com.example.githubjava.model.consultive

import android.util.Log
import com.example.githubjava.api.EndpointRepositorios
import com.example.githubjava.api.network.NetworkUtils
import com.example.githubjava.dao.RepositorioDao
import com.example.githubjava.model.Repositorio
import com.example.githubjava.ui.recyclerview.adapter.ListaRepositoriosAdapter
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositorioConsultive(
    val dao: RepositorioDao,
    val adapter: ListaRepositoriosAdapter
) {


    fun consultaApiGit(paginaAtual:Int) {
        buscandoRepositorios(paginaAtual)
        adapter.atualiza(dao.buscaTodosRepositorios())
        dao.removeTodosRepositorios()
        adapter.atualiza(dao.buscaTodosRepositorios())
    }

   private fun buscandoRepositorios(page: Int) {
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
                            nomeRepositorio =formataString(getItems?.asJsonObject?.get("name").toString()),
                            descricaoRepositorio = formataString(getItems?.asJsonObject?.get("description").toString()),
                            nomeAutor = formataString(getOwner?.asJsonObject?.get("login").toString()),
                            fotoAutor = formataString(getOwner?.asJsonObject?.get("avatar_url").toString()),
                            numeroStars = formataString(getItems?.asJsonObject?.get("stargazers_count").toString()),
                            numeroForks = formataString(getItems?.asJsonObject?.get("forks").toString())
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

    fun formataString(text:String):String{
        var textModified = text.substring(1, text.length -1)
        return textModified
    }

}