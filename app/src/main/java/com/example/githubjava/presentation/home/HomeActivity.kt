package com.example.githubjava.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.githubjava.data.dao.RepositorioDao
import com.example.githubjava.databinding.ActivityHomeBinding
import com.example.githubjava.data.model.Repositorio
import com.example.githubjava.presentation.pullRequest.PullRequestActivity
import com.example.githubjava.presentation.viewmodel.HomeViewModel
import com.example.githubjava.ui.adapter.ListaRepositoriosAdapter



class HomeActivity : AppCompatActivity(), ListaRepositoriosAdapter.SelecionaRepositorio {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

   private val homeViewModel = HomeViewModel(this)

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
