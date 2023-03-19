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

   suspend fun consultaApiGit(paginaAtual:Int){
        buscandoRepositorios(paginaAtual)

//        adapter.atualiza(dao.buscaTodosRepositorios())
//        dao.removeTodosRepositorios()
//        adapter.atualiza(dao.buscaTodosRepositorios())
    }

   private suspend fun buscandoRepositorios(page: Int) {
       val fetchCurrencies = repositoryImpl.fetchCurrencies(page.toString())
        val page = page
        if (page < 1) {
            page == 1
        }

       fetchCurrencies.enqueue(object : Callback<JsonObject> {
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
//        adapter.atualiza(dao.buscaTodosRepositorios())
    }

    fun formataString(text:String):String{
        var textModified = text.substring(1, text.length -1)
        return textModified
   }

}