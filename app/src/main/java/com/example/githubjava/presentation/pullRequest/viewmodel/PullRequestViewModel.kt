package com.example.githubjava.presentation.pullRequest.viewmodel

import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjava.data.dao.PullRequestDaoImpl
import com.example.githubjava.data.model.consultive.PullRequestConsultive
import com.example.githubjava.presentation.pullRequest.PullRequestActivity
import com.example.githubjava.presentation.pullRequest.state.PullRequestState
import com.example.githubjava.ui.adapter.ListaPullRequestsAdapter
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject


class PullRequestViewModel(
    pullRequestActivity: PullRequestActivity
) : ViewModel()
{

    private var dao = PullRequestDaoImpl()
    private var adapter =
        ListaPullRequestsAdapter(context = pullRequestActivity, pullRequests = dao.buscaTodosPullRequests())

    private val _state by lazy { MutableLiveData<PullRequestState>() }
    val state: LiveData<PullRequestState> = _state

    private val pullRequestConsultive : PullRequestConsultive by inject(PullRequestConsultive::class.java)

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