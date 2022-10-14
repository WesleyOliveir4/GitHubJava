package com.example.githubjava.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubjava.dao.PullRequestDao
import com.example.githubjava.dao.RepositorioDao
import com.example.githubjava.databinding.ActivityHomeBinding
import com.example.githubjava.databinding.ActivityPullRequestBinding
import com.example.githubjava.model.PullRequests
import com.example.githubjava.model.Repositorio
import com.example.githubjava.ui.recyclerview.adapter.ListaPullRequestsAdapter
import com.example.githubjava.ui.recyclerview.adapter.ListaRepositoriosAdapter

class PullRequestActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPullRequestBinding.inflate(layoutInflater)
    }
    private var dao = PullRequestDao()
    private var adapter =
        ListaPullRequestsAdapter(context = this, pullRequests = dao.buscaTodosPullRequests())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        configuraRecyclerView()
        addPullRequest()
    }

    private fun configuraRecyclerView() {
        var recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
    }

    fun addPullRequest(

    ) {

        val pullRequestNovo = PullRequests(
            nomeAutorPullrequests = "wesley",
            tituloPullRequests = "crud java",
            dataPullRequests = "14/10/2022",
            bodyPullRequest = "teste body",
        )
        dao.adicionaPullRequest(pullRequestNovo)
        adapter.atualiza(dao.buscaTodosPullRequests())
    }

    fun formataString(text: String): String {
        var textModified = text.substring(1, text.length - 1)
        return textModified
    }
}