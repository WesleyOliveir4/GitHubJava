package com.example.githubjava.data.dao

import com.example.githubjava.data.model.Repositorio

class RepositorioDao {

    fun adicionaRepositorio(repositorio: Repositorio){
        repositorios.add(repositorio)
    }

    fun buscaTodosRepositorios(): List<Repositorio>{
        return repositorios.toList()
    }

    fun removeTodosRepositorios(){
        repositorios.clear()
    }

    companion object {
        private val repositorios = mutableListOf<Repositorio>()
    }

//    fun buscandoRepositorios(page: Int):List<Repositorio> {
//        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com/search/")
//        val endpoint = retrofitClient.create(EndpointRepositorios::class.java)
//        val page = page
//        if (page < 1) {
//            page == 1
//        }
//
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
//                        addRepositorioNovo(
//                            nomeRepositorio = formataString(getItems?.asJsonObject?.get("name").toString()),
//                            descricaoRepositorio = formataString(getItems?.asJsonObject?.get("description").toString()),
//                            nomeAutor = formataString(getOwner?.asJsonObject?.get("login").toString()),
//                            fotoAutor = formataString(getOwner?.asJsonObject?.get("avatar_url").toString()),
//                            numeroStars = getItems?.asJsonObject?.get("stargazers_count").toString(),
//                            numeroForks = getItems?.asJsonObject?.get("forks").toString()
//                        )
//                        if (i == 29) {
//                            return
//                        }
//                        i++
//
//                    }
//                } catch (e: Exception) {
//                    Log.d("Erro ao pesquisar repo", i.toString() + e.toString())
//                }
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//
//            }
//
//        })
//        return repositorios.toList()
//
//    }
//
//    fun addRepositorioNovo(
//        nomeRepositorio: String,
//        descricaoRepositorio: String,
//        nomeAutor: String,
//        fotoAutor: String,
//        numeroStars: String,
//        numeroForks: String
//    ){
//
//        val repositoroNovo = Repositorio(
//            nomeRepositorio = nomeRepositorio,
//            descricaoRepositorio = descricaoRepositorio,
//            nomeAutor = nomeAutor,
//            fotoAutor = fotoAutor,
//            numeroStars = numeroStars,
//            numeroForks = numeroForks
//        )
//        adiciona(repositoroNovo)
//
//    }
//    fun formataString(text:String):String{
//        var textModified = text.substring(1, text.length -1)
//        return textModified
//    }

}