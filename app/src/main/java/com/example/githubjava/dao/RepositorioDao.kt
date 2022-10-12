package com.example.githubjava.dao

import android.util.Log
import com.example.githubjava.api.EndpointRepositorios
import com.example.githubjava.api.network.NetworkUtils
import com.example.githubjava.databinding.ActivityHomeBinding
import com.example.githubjava.model.Repositorio
import com.example.githubjava.ui.activity.HomeActivity
import com.example.githubjava.ui.recyclerview.adapter.ListaRepositoriosAdapter
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositorioDao {

    fun adiciona(repositorio: Repositorio){
        repositorios.add(repositorio)
    }

    fun buscaTodos(): List<Repositorio>{
        return repositorios.toList()
    }

    companion object {
        private val repositorios = mutableListOf<Repositorio>()
    }

     fun  buscandoRepositorios(page: Int){
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com/search/")
        val endpoint = retrofitClient.create(EndpointRepositorios::class.java)

        if(page<1){page==1}

        endpoint.getCurrencies("${page.toString()}").enqueue(object : Callback<JsonObject> {
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
                            getItems?.asJsonObject?.get("name").toString(),
                            getItems?.asJsonObject?.get("description").toString(),
                            getOwner?.asJsonObject?.get("login").toString(),
                            getItems?.asJsonObject?.get("stargazers_count").toString(),
                            getItems?.asJsonObject?.get("forks").toString()
                        )
                        Log.d(
                            "Teste deu certo owner",
                            getItems.toString()
                        )
                        i++
                    }
                } catch (e: Exception) {
                    Log.d("chegou ao limite", i.toString())
                }

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("Teste deu errado", "onFailure")
            }

        })

    }

    fun addRepositorioNovo(
        nomeRepositorio: String,
        descricaoRepositorio: String,
        nomeAutor: String,
        numeroStars: String,
        numeroForks: String
    ) {

        val repositoroNovo = Repositorio(
            nomeRepositorio = nomeRepositorio,
            descricaoRepositorio = descricaoRepositorio,
            nomeAutor = nomeAutor,
            numeroStars = numeroStars,
            numeroForks = numeroForks
        )
       adiciona(repositoroNovo)

    }

}