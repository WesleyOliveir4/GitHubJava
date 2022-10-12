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


}