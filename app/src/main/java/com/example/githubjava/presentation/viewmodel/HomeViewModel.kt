package com.example.githubjava.presentation.viewmodel

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjava.data.request.EndpointRepositorios
import com.example.githubjava.data.api.network.NetworkUtils
import com.example.githubjava.data.dao.RepositorioDao
import com.example.githubjava.data.dao.RepositorioDaoImpl
import com.example.githubjava.data.models.Repositorio
import com.example.githubjava.data.repository.PullRequestRepositoryImpl
import com.example.githubjava.data.repository.RepositorioRepositoryImpl
import com.example.githubjava.data.model.consultive.RepositorioConsultive
import com.example.githubjava.presentation.home.HomeActivity
import com.example.githubjava.presentation.state.HomeState
import com.example.githubjava.ui.adapter.ListaRepositoriosAdapter
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(homeActivity: HomeActivity) : ViewModel() {

    private val _state by lazy { MutableLiveData<HomeState>() }
    val state: LiveData<HomeState> = _state

    private var paginaAtual: Int = 1

    private val repositorioConsultive: RepositorioConsultive = RepositorioConsultive()

    private val repositoryImpl: RepositorioRepositoryImpl = RepositorioRepositoryImpl()
    private var dao = RepositorioDaoImpl()
    private var adapter = ListaRepositoriosAdapter(
        context = homeActivity,
        repositorios = dao.buscaTodosRepositorios(),
        selecionaRepositorio = homeActivity
    )


    fun configuraRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
    }

    fun configuraPaginacao(btnAnterior: Button, btnSeguinte: Button, tvNumeroPagina: TextView) {

        tvNumeroPagina.text = paginaAtual.toString()

        btnAnterior.setOnClickListener(View.OnClickListener {
            if (paginaAtual > 1) {
                paginaAtual -= 1
                tvNumeroPagina.text = paginaAtual.toString()
                consultaApiGit()
                dao.removeTodosRepositorios()
                adapter.atualiza(dao.buscaTodosRepositorios())
            } else {
                return@OnClickListener
            }
        })

        btnSeguinte.setOnClickListener(View.OnClickListener {
            paginaAtual += 1
            tvNumeroPagina.text = paginaAtual.toString()
            consultaApiGit()
            dao.removeTodosRepositorios()
            adapter.atualiza(dao.buscaTodosRepositorios())
        })
    }

    fun consultaApiGit() {
        buscandoRepositorios(paginaAtual)
    }

    fun buscandoRepositorios(page: Int) {
        viewModelScope.launch {
            val resultado = repositorioConsultive.consultaApiGit(page)
            adapter.atualiza(dao.buscaTodosRepositorios())
            _state.postValue(HomeState.ShowItems(resultado.toMutableList()))
        }
    }

}