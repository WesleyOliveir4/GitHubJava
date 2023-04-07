package com.example.githubjava.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjava.data.request.EndpointPullRequest
import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.dao.PullRequestDao
import com.example.githubjava.data.dao.PullRequestDaoImpl
import com.example.githubjava.data.model.consultive.PullRequestConsultive
import com.example.githubjava.data.models.PullRequests
import com.example.githubjava.data.repository.PullRequestRepositoryImpl
import com.example.githubjava.presentation.pullRequest.PullRequestActivity
import com.example.githubjava.presentation.state.HomeState
import com.example.githubjava.presentation.state.PullRequestState
import com.example.githubjava.ui.adapter.ListaPullRequestsAdapter
import com.google.gson.JsonArray
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestViewModel(
    pullRequestActivity: PullRequestActivity,
    private val repositoryImpl:PullRequestRepositoryImpl = PullRequestRepositoryImpl()
) : ViewModel()
{

    private var dao = PullRequestDaoImpl()
    private var adapter =
        ListaPullRequestsAdapter(context = pullRequestActivity, pullRequests = dao.buscaTodosPullRequests())

    private val _state by lazy { MutableLiveData<PullRequestState>() }
    val state: LiveData<PullRequestState> = _state

    private val pullRequestConsultive : PullRequestConsultive = PullRequestConsultive()

    fun configuraRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
    }
    fun buscandoPullRequests(nomeCriador: String, nomeRepositorio: String) {

        dao.removeTodosPullRequests()
        viewModelScope.launch {
            val resultado = pullRequestConsultive.consultaPullRequest(nomeCriador,nomeRepositorio)
            adapter.atualiza(dao.buscaTodosPullRequests())
            _state.postValue(PullRequestState.ShowItems(resultado.toMutableList()))
        }

        adapter.atualiza(dao.buscaTodosPullRequests())

    }

}