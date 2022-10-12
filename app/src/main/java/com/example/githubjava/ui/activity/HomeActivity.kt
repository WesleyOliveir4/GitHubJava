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
        consultaApiGit()
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        consultaApiGit()
    }

    fun consultaApiGit(){
        dao.buscandoRepositorios(1)
        adapter.atualiza(dao.buscaTodos())
        onRestart()
    }

//    fun buscandoRepositorios() {
//        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com/search/")
//        val endpoint = retrofitClient.create(EndpointRepositorios::class.java)
//        val page = "page=2"
//        endpoint.getCurrencies("$page").enqueue(object : Callback<JsonObject> {
//            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                var i: Int = 1
//
//                val objeto = response.body()?.get("items")
//                try {
//                    objeto?.asJsonArray?.forEach {
//                        val getOwners = objeto?.asJsonArray?.get(i)
//                        val getOwner = getOwners?.asJsonObject?.get("owner")
//                        val getItems = objeto?.asJsonArray?.get(i)
//                        getItems?.asJsonObject?.get("name")
//
//
//                        addRepositorioNovo(
//                            getItems?.asJsonObject?.get("name").toString(),
//                            getItems?.asJsonObject?.get("description").toString(),
//                            getOwner?.asJsonObject?.get("login").toString(),
//                            getItems?.asJsonObject?.get("stargazers_count").toString(),
//                            getItems?.asJsonObject?.get("forks").toString()
//                        )
////                        Log.d(
////                            "Teste deu certo owner",
////                            getItems.toString()
////                        )
//                        i++
//                    }
//                } catch (e: Exception) {
//                    Log.d("chegou ao limite", i.toString())
//                }
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                Log.d("Teste deu errado", "onFailure")
//            }
//
//        })

    }
