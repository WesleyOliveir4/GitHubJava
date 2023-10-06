package com.example.githubjava.presentation.repositorio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubjava.domain.models.Repositorio
import com.example.githubjava.domain.repositorio.SearchRepositorioUseCase
import com.example.githubjava.presentation.repositorio.state.HomeState
import kotlinx.coroutines.launch

class RepositorioViewModel(private val searchRepositorioUseCase: SearchRepositorioUseCase) : ViewModel() {

    private val _state by lazy { MutableLiveData<HomeState>() }
    val state: LiveData<HomeState> = _state
    companion object {
        private var paginaAtual: Int = 1
        val reposReturned: MutableList<Repositorio> = emptyList<Repositorio>().toMutableList()
    }

    fun configuraPaginacao(lastItem: Boolean) {
        if(lastItem){
            paginaAtual += 1
            buscandoRepositorios()
        }else{
            paginaAtual = 1
            buscandoRepositorios()
        }
    }

    fun buscandoRepositorios() {
        viewModelScope.launch {
            reposReturned.addAll(searchRepositorioUseCase.fetchCurrencies(paginaAtual.toString()))
            _state.postValue(HomeState.ShowItems(reposReturned))
        }
    }


}