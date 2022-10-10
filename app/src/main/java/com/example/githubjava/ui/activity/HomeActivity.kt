package com.example.githubjava.ui.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.githubjava.api.EndpointRepositorios
import com.example.githubjava.api.network.NetworkUtils
import com.example.githubjava.dao.RepositorioDao
import com.example.githubjava.databinding.ActivityHomeBinding
import com.example.githubjava.model.Repositorio
import com.example.githubjava.ui.recyclerview.adapter.ListaRepositoriosAdapter
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create


class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val dao = RepositorioDao()
    private val adapter = ListaRepositoriosAdapter(context = this, repositorios = dao.buscaTodos())
    ///


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        configuraRecyclerView()
        buscandoRepositorios()


    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()


        adapter.atualiza(dao.buscaTodos())
    }

    fun buscandoRepositorios() {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com/search/")
        val endpoint = retrofitClient.create(EndpointRepositorios::class.java)
        val page = "page=1"
        endpoint.getCurrencies("$page").enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                var data = mutableListOf<String>()
                var i: Int = 1

                val objeto = response.body()?.get("items")
                try {
                    objeto?.asJsonArray?.forEach {
                        val getOwners = objeto?.asJsonArray?.get(i)
                        val getOwner = getOwners?.asJsonObject?.get("owner")
                        getOwner?.asJsonObject?.get("login")
                        addRepositorioNovo(
                            getOwner?.asJsonObject?.get("login").toString(),
                            getOwner?.asJsonObject?.get("login").toString(),
                            getOwner?.asJsonObject?.get("login").toString(),
                            getOwner?.asJsonObject?.get("login").toString(),
                            getOwner?.asJsonObject?.get("login").toString()
                        )
                        Log.d(
                            "Teste deu certo owner",
                            getOwner?.asJsonObject?.get("description").toString()
                        )
                        i++
                    }
                } catch (e: Exception) {
                    Log.d("chegou ao limite", i.toString())
                }

//                        try {
//                            val getOwner = getItem?.asJsonObject?.get("owner")
//                            Log.d("Teste deu certo owner",getItem.toString())
//                            val getItems = objeto?.asJsonArray?.get(i)
//                            val findName = getItems?.asJsonObject?.entrySet()?.find { it.key == "login" }
//                            val findName = getItens?.asJsonObject?.entrySet()?.find { it.key == "full_name" }
//                            val findName = getItens?.asJsonObject?.entrySet()?.find { it.key == "full_name" }
//                            val findName = getItens?.asJsonObject?.entrySet()?.find { it.key == "full_name" }

//                            Log.d("Teste deu certo",findName.toString())
//                            i++
//                        }catch (e: Exception){
//                            Log.d("chegou ao limite",i.toString())
//                        }


//                        val find = get?.asJsonObject?.entrySet()?.find { it.key == "full_name" }


//                response.body()?.keySet()?.iterator()?.forEach {
//                    data.add(it)
//                    println(data.count())

//                }
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
        dao.adiciona(repositoroNovo)
    }


}