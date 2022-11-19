package com.example.githubjava.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.githubjava.dao.RepositorioDao
import com.example.githubjava.databinding.ActivityHomeBinding
import com.example.githubjava.model.consultive.RepositorioConsultive
import com.example.githubjava.model.Repositorio
import com.example.githubjava.ui.recyclerview.adapter.ListaRepositoriosAdapter


class HomeActivity : AppCompatActivity(), ListaRepositoriosAdapter.SelecionaRepositorio {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private var dao = RepositorioDao()
    private var adapter = ListaRepositoriosAdapter(context = this, repositorios = dao.buscaTodosRepositorios(), selecionaRepositorio = this)
    private var paginaAtual:Int = 1
    private var repositorioConsultive =  RepositorioConsultive(dao = dao, adapter = adapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
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
                repositorioConsultive.consultaApiGit(paginaAtual)
            }else{
                return@OnClickListener
            }
        })

        btnSeguinte.setOnClickListener(View.OnClickListener {
            paginaAtual += 1
            tvNumeroPagina.text = paginaAtual.toString()
            repositorioConsultive.consultaApiGit(paginaAtual)
        })
    }

    override fun onResume() {
        super.onResume()
        setFullScreen()
        configuraRecyclerView()
        configuraPaginacao()
        repositorioConsultive.consultaApiGit(paginaAtual)
    }

    private fun setFullScreen(){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
    override fun selecionaRepositorio(repositorio: Repositorio) {

        val intent = Intent(this,PullRequestActivity::class.java).apply {
            putExtra("repositorio.nomeAutor",repositorio.nomeAutor)
            putExtra("repositorio.nomeRepositorio",repositorio.nomeRepositorio)
        }
        startActivity(intent)
    }

}
