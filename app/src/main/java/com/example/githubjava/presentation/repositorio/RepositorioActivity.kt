package com.example.githubjava.presentation.repositorio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import com.example.githubjava.databinding.ActivityHomeBinding
import com.example.githubjava.di.injectRepositorioViewModelModule
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
    init {
        injectRepositorioViewModelModule()
    }

    private val repositorioViewModel : RepositorioViewModel by viewModel()

    private var adapter = ListaRepositoriosAdapter(context = this, this )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        repositorioViewModel.configuraPaginacao(false)
    }

    override fun onResume() {
        super.onResume()

        val recyclerView = binding.recyclerView
        val nestedScrollViewRepositorios = binding.NestedScrollViewRepositorios
        val progressBarLoadingRepositorios = binding.ProgressBarLoadingRepositorios


        repositorioViewModel.state.observe(this, Observer { state ->
            when(state){
                is HomeState.ShowItems -> {
                    recyclerView.adapter = adapter
                    adapter.submitList(state.items)
                    progressBarLoadingRepositorios.visibility = View.INVISIBLE
                    Log.d("stateHomeActivity", "${state.items[0].nomeRepositorio}")
                    Log.d("stateHomeActivityTotal", "${state.items.size}")
                }
                else -> Log.d("stateHomeActivity", "retornou com erro")
            }
        })


        nestedScrollViewRepositorios.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener{
                v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if(scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                progressBarLoadingRepositorios.visibility = View.VISIBLE
                repositorioViewModel.configuraPaginacao(true)
            }
        })

    }

    override fun selecionaRepositorio(repositorio: Repositorio) {

        val intent = Intent(this, PullRequestActivity::class.java).apply {
            putExtra("repositorio.nomeAutor",repositorio.nomeAutor)
            putExtra("repositorio.nomeRepositorio",repositorio.nomeRepositorio)
        }
        startActivity(intent)
    }

}
