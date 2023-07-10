package com.example.githubjava.presentation.repositorio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.githubjava.databinding.ActivityHomeBinding
import com.example.githubjava.domain.models.Repositorio
import com.example.githubjava.presentation.repositorio.state.HomeState
import com.example.githubjava.presentation.repositorio.viewmodel.RepositorioViewModel
import com.example.githubjava.presentation.pullRequest.PullRequestActivity
import com.example.githubjava.ui.adapter.ListaRepositoriosAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class RepositorioActivity : AppCompatActivity(), ListaRepositoriosAdapter.SelecionaRepositorio {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

//   private val repositorioViewModel = RepositorioViewModel()

    /**
     * Problema para criar um by viewModel() para o RepositorioActivity
     */
    private val repositorioViewModel : RepositorioViewModel by viewModel()

    private var adapter = ListaRepositoriosAdapter(context = this, repositorios = mutableListOf(), this )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()

        val recyclerView = binding.recyclerView
        val btnAnterior = binding.btnAnterior
        val btnSeguinte = binding.btnSeguinte
        val tvNumeroPagina = binding.tvNumeroPagina

        repositorioViewModel.state.observe(this, Observer { state ->
            when(state){
                is HomeState.ShowItems -> {
                    recyclerView.adapter = adapter
                    adapter.submitList(state.items)
                    Log.d("stateHomeActivity", "${state.items[0].nomeRepositorio}")
                }
                else -> Log.d("stateHomeActivity", "retornou com erro")
            }
        })

        repositorioViewModel.configuraPaginacao(btnAnterior, btnSeguinte, tvNumeroPagina)
        repositorioViewModel.consultaApiGit()

    }

    override fun selecionaRepositorio(repositorio: Repositorio) {

        val intent = Intent(this, PullRequestActivity::class.java).apply {
            putExtra("repositorio.nomeAutor",repositorio.nomeAutor)
            putExtra("repositorio.nomeRepositorio",repositorio.nomeRepositorio)
        }
        startActivity(intent)
    }

}
