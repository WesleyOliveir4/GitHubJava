package com.example.githubjava.presentation.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.githubjava.databinding.ActivityHomeBinding
import com.example.githubjava.data.models.Repositorio
import com.example.githubjava.presentation.home.state.HomeState
import com.example.githubjava.presentation.home.viewmodel.HomeViewModel
import com.example.githubjava.presentation.pullRequest.PullRequestActivity
import com.example.githubjava.ui.adapter.ListaRepositoriosAdapter



class HomeActivity : AppCompatActivity(), ListaRepositoriosAdapter.SelecionaRepositorio {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val homeViewModel = HomeViewModel(this)

    private var adapter = ListaRepositoriosAdapter(context = this, repositorios = mutableListOf(), this )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }

    override fun onResume() {
        super.onResume()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val recyclerView = binding.recyclerView
        val btnAnterior = binding.btnAnterior
        val btnSeguinte = binding.btnSeguinte
        val tvNumeroPagina = binding.tvNumeroPagina

        homeViewModel.state.observe(this, Observer { state ->
            when(state){
                is HomeState.ShowItems -> {
                    recyclerView.adapter = adapter
                    adapter.atualiza(state.items)
                    Log.d("stateHomeActivity", "${state.items[0].nomeRepositorio}")
                }
                else -> Log.d("stateHomeActivity", "retornou com erro")
            }
        })

        homeViewModel.configuraRecyclerView(recyclerView)
        homeViewModel.configuraPaginacao(btnAnterior, btnSeguinte, tvNumeroPagina)
        homeViewModel.consultaApiGit()

    }

    override fun selecionaRepositorio(repositorio: Repositorio) {

        val intent = Intent(this, PullRequestActivity::class.java).apply {
            putExtra("repositorio.nomeAutor",repositorio.nomeAutor)
            putExtra("repositorio.nomeRepositorio",repositorio.nomeRepositorio)
        }
        startActivity(intent)
    }

}
