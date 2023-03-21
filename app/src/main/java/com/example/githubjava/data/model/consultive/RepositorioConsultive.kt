package com.example.githubjava.data.model.consultive

import android.util.Log
import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.dao.RepositorioDao
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
//    val dao: RepositorioDao,
//    val adapter: ListaRepositoriosAdapter
) {

    private val repositoryImpl: RepositorioRepositoryImpl = RepositorioRepositoryImpl()
    private val responseRepositorio: ResponseRepositorio = ResponseRepositorio()
    private val dao: RepositorioDao = RepositorioDao()

   suspend fun consultaApiGit(paginaAtual:Int): List<Repositorio> {
        return buscandoRepositorios(paginaAtual)

//        adapter.atualiza(dao.buscaTodosRepositorios())
//        dao.removeTodosRepositorios()
//        adapter.atualiza(dao.buscaTodosRepositorios())
    }

   private suspend fun buscandoRepositorios(page: Int): List<Repositorio> {
       val fetchCurrencies = repositoryImpl.fetchCurrencies(page.toString())
        val page = page
        if (page < 1) {
            page == 1
        }

     return fetchCurrencies


    }

    fun addRepositorioNovo(
        nomeRepositorio: String
//        descricaoRepositorio: String,
//        nomeAutor: String,
//        fotoAutor: String,
//        numeroStars: String,
//        numeroForks: String
    ) {

        val repositoroNovo = Repositorio(
            nomeRepositorio = nomeRepositorio,
//            descricaoRepositorio = descricaoRepositorio,
//            nomeAutor = nomeAutor,
//            fotoAutor = fotoAutor,
//            numeroStars = numeroStars,
//            numeroForks = numeroForks
        )
        dao.adicionaRepositorio(repositoroNovo)
//        adapter.atualiza(dao.buscaTodosRepositorios())
    }

    fun formataString(text:String):String{
        var textModified = text.substring(1, text.length -1)
        return textModified
   }

}