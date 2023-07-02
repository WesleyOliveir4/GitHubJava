package com.example.githubjava.presentation.pullRequest.viewmodel

import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjava.data.dao.PullRequestDaoImpl
import com.example.githubjava.data.model.consultive.SearchPullRequestUseCase
import com.example.githubjava.data.models.PullRequests
import com.example.githubjava.data.repository.PullRequestRepository
import com.example.githubjava.presentation.pullRequest.PullRequestActivity
import com.example.githubjava.presentation.pullRequest.state.PullRequestState
import com.example.githubjava.ui.adapter.ListaPullRequestsAdapter
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject


class PullRequestViewModel(
    pullRequestActivity: PullRequestActivity
) : ViewModel(), SearchPullRequestUseCase
{

    private var dao = PullRequestDaoImpl()
    private var adapter =
        ListaPullRequestsAdapter(context = pullRequestActivity, pullRequests = dao.buscaTodosPullRequests())

    private val _state by lazy { MutableLiveData<PullRequestState>() }
    val state: LiveData<PullRequestState> = _state

    private val pullRequestRepository : PullRequestRepository by inject(PullRequestRepository::class.java)

    fun configuraRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
    }
    fun buscandoPullRequests(nomeCriador: String, nomeRepositorio: String) {

        dao.removeTodosPullRequests()
        viewModelScope.launch {
            val resultado = consultaPullRequest(nomeCriador,nomeRepositorio)
            adapter.submitList(dao.buscaTodosPullRequests())
            _state.postValue(PullRequestState.ShowItems(resultado.toMutableList()))
        }

        adapter.submitList(dao.buscaTodosPullRequests())

    }

    override suspend fun consultaPullRequest(
        nomeCriador: String,
        nomeRepositorio: String
    ): MutableList<PullRequests> {
        return pullRequestRepository.fetchCurrencies(nomeCriador, nomeRepositorio)
    }

}