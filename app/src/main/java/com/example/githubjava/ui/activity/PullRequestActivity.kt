package com.example.githubjava.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.githubjava.api.EndpointPullRequest
import com.example.githubjava.api.network.NetworkUtils
import com.example.githubjava.dao.PullRequestDao
import com.example.githubjava.databinding.ActivityPullRequestBinding
import com.example.githubjava.model.PullRequests
import com.example.githubjava.model.consultive.PullRequestConsultive
import com.example.githubjava.ui.recyclerview.adapter.ListaPullRequestsAdapter
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPullRequestBinding.inflate(layoutInflater)
    }
    private var dao = PullRequestDao()
    private var adapter =
        ListaPullRequestsAdapter(context = this, pullRequests = dao.buscaTodosPullRequests())

    private val pullRequestConsultive = PullRequestConsultive(dao = dao, adapter = adapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        val nomeCriadorSelecionado = intent?.getStringExtra("repositorio.nomeAutor")
        val nomeRepositorioSelecionado = intent?.getStringExtra("repositorio.nomeRepositorio")
        setFullScreen()
        setButtonUpEnabled()

        configuraRecyclerView()
        pullRequestConsultive.buscandoPullRequests(nomeCriadorSelecionado.toString(),nomeRepositorioSelecionado.toString())
    }

    private fun configuraRecyclerView() {
        var recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
    }

    private fun setFullScreen(){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
    private fun setButtonUpEnabled(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

}