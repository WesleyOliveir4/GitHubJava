package com.example.githubjava.presentation.pullRequest.viewmodel

import androidx.lifecycle.*
import com.example.githubjava.domain.models.PullRequests
import com.example.githubjava.domain.pullRequest.SearchPullRequestUseCase
import com.example.githubjava.presentation.pullRequest.state.PullRequestState
import kotlinx.coroutines.launch


class PullRequestViewModel(private val searchPullRequestUseCase : SearchPullRequestUseCase) : ViewModel()
{

    private val _state by lazy { MutableLiveData<PullRequestState>() }
    val state: LiveData<PullRequestState> = _state


    fun buscandoPullRequests(nomeCriador: String, nomeRepositorio: String) {

        viewModelScope.launch {
            val resultado = searchPullRequestUseCase.fetchCurrencies(nomeCriador, nomeRepositorio)
            _state.postValue(PullRequestState.ShowItems(resultado.toMutableList()))
        }

    }

}