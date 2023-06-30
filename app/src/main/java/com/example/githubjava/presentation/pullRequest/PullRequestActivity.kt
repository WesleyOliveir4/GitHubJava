package com.example.githubjava.presentation.pullRequest

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.githubjava.databinding.ActivityPullRequestBinding
import com.example.githubjava.presentation.pullRequest.state.PullRequestState
import com.example.githubjava.presentation.pullRequest.viewmodel.PullRequestViewModel
import com.example.githubjava.ui.adapter.ListaPullRequestsAdapter

class PullRequestActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPullRequestBinding.inflate(layoutInflater)
    }

    private val pullRequestViewModel = PullRequestViewModel(this)

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
                    adapter.atualiza(state.items)
                    Log.d("stateHomeActivity", "${state.items[0].tituloPullRequests}")
                }
                else -> Log.d("stateHomeActivity", "retornou com erro")
            }
        })

        pullRequestViewModel.configuraRecyclerView(recyclerView)
        pullRequestViewModel.buscandoPullRequests(nomeCriadorSelecionado.toString(),nomeRepositorioSelecionado.toString())
    }


}