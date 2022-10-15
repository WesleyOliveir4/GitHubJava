package com.example.githubjava.ui.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjava.databinding.RepositorioItemBinding
import com.example.githubjava.extensoes.CarregaImagem
import com.example.githubjava.model.Repositorio
import java.util.*

class ListaRepositoriosAdapter(
    private val context: Context,
    repositorios: List<Repositorio>,
    var selecionaRepositorio: SelecionaRepositorio
): RecyclerView.Adapter<ListaRepositoriosAdapter.ViewHolder>() {

    private val repositorios = repositorios.toMutableList()
    interface SelecionaRepositorio{
        fun selecionaRepositorio(repositorio: Repositorio)
    }

    class ViewHolder(private val binding: RepositorioItemBinding) : RecyclerView.ViewHolder(binding.root) {
            val cardView =binding.cardViewRepositorio
        fun vincula(repositorio: Repositorio) {
            val nomeRepositorioTextView = binding.nomeRepositorioRecyclerView
            nomeRepositorioTextView.text = repositorio.nomeRepositorio
            val descricaoRepositorioTextView = binding.descricaoRepositorioRecyclerView
            descricaoRepositorioTextView.text = repositorio.descricaoRepositorio
            val nomeAutorTextView = binding.nomeAutorRecyclerView
            nomeAutorTextView.text = repositorio.nomeAutor
            binding.fotoAutorImageView.CarregaImagem(repositorio.fotoAutor)
            val numeroStarsTextView = binding.numeroStarsRecyclerView
            numeroStarsTextView.text = repositorio.numeroStars
            val numeroForksTextView = binding.numeroForksRecyclerView
            numeroForksTextView.text = repositorio.numeroForks

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = RepositorioItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repositorio = repositorios[position]
        holder.vincula(repositorio)

        holder.cardView.setOnClickListener(View.OnClickListener {
            selecionaRepositorio.selecionaRepositorio(repositorio)
        })
    }

    override fun getItemCount(): Int = repositorios.size
    fun atualiza(repositorios: List<Repositorio>) {
        notifyDataSetChanged()
        this.repositorios.clear()
        this.repositorios.addAll(repositorios)

    }



}