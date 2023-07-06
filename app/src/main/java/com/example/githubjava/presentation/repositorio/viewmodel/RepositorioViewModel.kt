package com.example.githubjava.presentation.repositorio.viewmodel

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubjava.data.model.consultive.SearchRepositorioUseCase
import com.example.githubjava.data.models.Repositorio
import com.example.githubjava.data.repository.RepositorioRepository
import com.example.githubjava.presentation.repositorio.RepositorioActivity
import com.example.githubjava.presentation.repositorio.state.HomeState
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class RepositorioViewModel() : ViewModel(), SearchRepositorioUseCase {

    private val _state by lazy { MutableLiveData<HomeState>() }
    val state: LiveData<HomeState> = _state
    companion object {
        private var paginaAtual: Int = 1
    }
    private val repositorioRepository: RepositorioRepository by inject(RepositorioRepository::class.java)

    fun configuraPaginacao(btnAnterior: Button, btnSeguinte: Button, tvNumeroPagina: TextView) {

        tvNumeroPagina.text = paginaAtual.toString()

        btnAnterior.setOnClickListener(View.OnClickListener {
            if (paginaAtual > 1) {
                paginaAtual -= 1
                tvNumeroPagina.text = paginaAtual.toString()
                consultaApiGit()
            } else {
                return@OnClickListener
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

    override suspend fun consultaRepositorio(paginaAtual: Int): List<Repositorio> {
        val page = paginaAtual
        if (page < 1) {
            page == 1
        }
        return repositorioRepository.fetchCurrencies(page.toString())
    }


    fun buscandoRepositorios(page: Int) {
        viewModelScope.launch {
            val resultado = consultaRepositorio(page)
            _state.postValue(HomeState.ShowItems(resultado.toMutableList()))
        }
    }

}