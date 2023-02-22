package com.example.githubjava.presentation.pullRequest

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.githubjava.databinding.ActivityPullRequestBinding
import com.example.githubjava.presentation.viewmodel.PullRequestModel

class PullRequestActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPullRequestBinding.inflate(layoutInflater)
    }

    private val pullRequestModel = PullRequestModel(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        val nomeCriadorSelecionado = intent?.getStringExtra("repositorio.nomeAutor")
        val nomeRepositorioSelecionado = intent?.getStringExtra("repositorio.nomeRepositorio")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val recyclerView = binding.recyclerView

        pullRequestModel.configuraRecyclerView(recyclerView)
        pullRequestModel.buscandoPullRequests(nomeCriadorSelecionado.toString(),nomeRepositorioSelecionado.toString())
    }


}