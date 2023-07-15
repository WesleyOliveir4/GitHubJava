package com.example.githubjava.presentation.pullRequest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.githubjava.databinding.ActivityPullRequestBinding
import com.example.githubjava.di.injectPullRequestViewModelModule
import com.example.githubjava.presentation.pullRequest.state.PullRequestState
import com.example.githubjava.presentation.pullRequest.viewmodel.PullRequestViewModel
import com.example.githubjava.ui.adapter.ListaPullRequestsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullRequestActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPullRequestBinding.inflate(layoutInflater)
    }

    init {
        injectPullRequestViewModelModule()
    }
    private val pullRequestViewModel: PullRequestViewModel by viewModel()

    private var adapter = ListaPullRequestsAdapter(context = this, pullRequests = mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        val nomeCriadorSelecionado = intent?.getStringExtra("repositorio.nomeAutor")
        val nomeRepositorioSelecionado = intent?.getStringExtra("repositorio.nomeRepositorio")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val recyclerView = binding.recyclerView

        pullRequestViewModel.state.observe(this, Observer { state ->
            when(state){
                is PullRequestState.ShowItems -> {
                    recyclerView.adapter = adapter
                    adapter.submitList(state.items)
                    Log.d("stateHomeActivity", "${state.items[0].tituloPullRequests}")
                }
                else -> Log.d("stateHomeActivity", "retornou com erro")
            }
        })

        pullRequestViewModel.buscandoPullRequests(nomeCriadorSelecionado.toString(),nomeRepositorioSelecionado.toString())
    }


}