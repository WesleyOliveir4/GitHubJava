package com.example.githubjava.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.text.set
import com.example.githubjava.api.EndpointRepositorios
import com.example.githubjava.api.network.NetworkUtils
import com.example.githubjava.dao.RepositorioDao
import com.example.githubjava.databinding.ActivityHomeBinding
import com.example.githubjava.model.Repositorio
import com.example.githubjava.ui.recyclerview.adapter.ListaRepositoriosAdapter
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import retrofit2.http.Url


class HomeActivity : AppCompatActivity(), ListaRepositoriosAdapter.SelecionaRepositorio {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private var dao = RepositorioDao()
    private var adapter = ListaRepositoriosAdapter(context = this, repositorios = dao.buscaTodosRepositorios(), selecionaRepositorio = this)
    private var paginaAtual:Int = 1
    ///


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
//        configuraRecyclerView()
//        configuraPaginacao()
//        consultaApiGit()
    }

    private fun configuraRecyclerView() {
        var recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
    }

    private fun configuraPaginacao(){
        val btnAnterior = binding.btnAnterior
        val btnSeguinte = binding.btnSeguinte
        var tvNumeroPagina = binding.tvNumeroPagina
        tvNumeroPagina.text = paginaAtual.toString()

        btnAnterior.setOnClickListener(View.OnClickListener {
            if(paginaAtual>1){
                paginaAtual -= 1
                tvNumeroPagina.text = paginaAtual.toString()
                consultaApiGit()
                dao.removeTodosRepositorios()
                adapter.atualiza(dao.buscaTodosRepositorios())
            }else{
                return@OnClickListener
            }
        })

        btnSeguinte.setOnClickListener(View.OnClickListener {
            paginaAtual += 1
            tvNumeroPagina.text = paginaAtual.toString()
            consultaApiGit()
            dao.removeTodosRepositorios()
            redirecionaPull()
            adapter.atualiza(dao.buscaTodosRepositorios())
        })
    }

    override fun onResume() {
        super.onResume()
        configuraRecyclerView()
        configuraPaginacao()
        consultaApiGit()
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
                            nomeRepositorio = formataString(getItems?.asJsonObject?.get("name").toString()),
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


    private fun redirecionaPull(){
        val intent = Intent(this,PullRequestActivity::class.java)

        startActivity(intent)
    }

    override fun selecionaRepositorio(repositorio: Repositorio) {

        Toast.makeText(this, repositorio.toString(), Toast.LENGTH_SHORT).show()
        val intent = Intent(this,PullRequestActivity::class.java).apply {
            putExtra("repositorio.nomeAutor",repositorio.nomeAutor)
            putExtra("repositorio.nomeRepositorio",repositorio.nomeRepositorio)
        }
        startActivity(intent)
    }

}
