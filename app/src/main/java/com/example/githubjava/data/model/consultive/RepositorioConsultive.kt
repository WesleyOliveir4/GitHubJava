package com.example.githubjava.data.model.consultive

import android.util.Log
import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.dao.RepositorioDao
import com.example.githubjava.data.dao.RepositorioDaoImpl
import com.example.githubjava.data.mapper.ResponseRepositorio
import com.example.githubjava.data.models.Repositorio
import com.example.githubjava.data.repository.PullRequestRepositoryImpl
import com.example.githubjava.data.repository.RepositorioRepositoryImpl
import com.example.githubjava.data.request.EndpointRepositorios
import com.example.githubjava.ui.adapter.ListaRepositoriosAdapter
import com.google.gson.JsonObject
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositorioConsultive(
) {

    private val repositoryImpl: RepositorioRepositoryImpl = RepositorioRepositoryImpl()
    private val responseRepositorio: ResponseRepositorio = ResponseRepositorio()
    private val dao: RepositorioDao = RepositorioDaoImpl()

   suspend fun consultaApiGit(paginaAtual:Int): List<Repositorio> {
        return buscandoRepositorios(paginaAtual)
    }

   private suspend fun buscandoRepositorios(pageReceived: Int): List<Repositorio> {

        val page = pageReceived
        if (page < 1) {
            page == 1
        }
       val fetchCurrencies = repositoryImpl.fetchCurrencies(page.toString())
       fetchCurrencies.map { repositorio ->
           dao.adicionaRepositorio(repositorio)
       }

     return fetchCurrencies
    }

}