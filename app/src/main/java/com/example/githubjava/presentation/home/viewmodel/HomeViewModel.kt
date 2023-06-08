package com.example.githubjava.presentation.home.viewmodel

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjava.data.dao.RepositorioDaoImpl
import com.example.githubjava.data.model.consultive.RepositorioConsultive
import com.example.githubjava.presentation.home.HomeActivity
import com.example.githubjava.presentation.home.state.HomeState
import com.example.githubjava.ui.adapter.ListaRepositoriosAdapter
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class HomeViewModel(
    homeActivity: HomeActivity
) : ViewModel() {

    private val _state by lazy { MutableLiveData<HomeState>() }
    val state: LiveData<HomeState> = _state
    companion object {
        private var paginaAtual: Int = 1
    }
    private val repositorioConsultive: RepositorioConsultive by inject(RepositorioConsultive::class.java)

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