package com.example.githubjava.presentation.repositorio.viewmodel

import android.view.View
import android.widget.Button
import android.widget.TextView
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
    }

    fun configuraPaginacao(btnAnterior: Button, btnSeguinte: Button, tvNumeroPagina: TextView) {

        tvNumeroPagina.text = paginaAtual.toString()

        btnAnterior.setOnClickListener(View.OnClickListener {
            if (paginaAtual > 1) {
                paginaAtual -= 1
                tvNumeroPagina.text = paginaAtual.toString()
                consultaApiGit()
            }
        })

        btnSeguinte.setOnClickListener(View.OnClickListener {
            paginaAtual += 1
            tvNumeroPagina.text = paginaAtual.toString()
            consultaApiGit()
        })
    }

    fun consultaApiGit() {
        buscandoRepositorios(paginaAtual)
    }

    fun buscandoRepositorios(page: Int) {
        viewModelScope.launch {
            if (page < 1) {
                page == 1
            }
            val resultado = searchRepositorioUseCase.fetchCurrencies(page.toString())
            _state.postValue(HomeState.ShowItems(resultado.toMutableList()))
        }
    }

//   suspend fun consultaRepositorio(paginaAtual: Int): List<Repositorio> {
//        val page = paginaAtual
//        if (page < 1) {
//            page == 1
//        }
//        return searchRepositorioUseCase.fetchCurrencies(page.toString())
//    }




}