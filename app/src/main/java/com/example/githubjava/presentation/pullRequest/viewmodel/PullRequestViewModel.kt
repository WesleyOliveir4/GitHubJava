package com.example.githubjava.presentation.pullRequest.viewmodel

import androidx.lifecycle.*
import com.example.githubjava.data.model.consultive.SearchPullRequestUseCase
import com.example.githubjava.data.models.PullRequests
import com.example.githubjava.data.repository.PullRequestRepository
import com.example.githubjava.presentation.pullRequest.state.PullRequestState
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject


class PullRequestViewModel() : ViewModel(), SearchPullRequestUseCase
{

    private val _state by lazy { MutableLiveData<PullRequestState>() }
    val state: LiveData<PullRequestState> = _state

    private val pullRequestRepository : PullRequestRepository by inject(PullRequestRepository::class.java)

    fun buscandoPullRequests(nomeCriador: String, nomeRepositorio: String) {

        viewModelScope.launch {
            val resultado = consultaPullRequest(nomeCriador,nomeRepositorio)
            _state.postValue(PullRequestState.ShowItems(resultado.toMutableList()))
        }

    }

    override suspend fun consultaPullRequest(
        nomeCriador: String,
        nomeRepositorio: String
    ): MutableList<PullRequests> {
        return pullRequestRepository.fetchCurrencies(nomeCriador, nomeRepositorio)
    }

}