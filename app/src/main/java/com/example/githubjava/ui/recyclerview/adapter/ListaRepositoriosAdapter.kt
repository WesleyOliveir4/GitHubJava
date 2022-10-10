package com.example.githubjava.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjava.databinding.RepositorioItemBinding
import com.example.githubjava.model.Repositorio
import java.util.*

class ListaRepositoriosAdapter(
    private val context: Context,
    repositorios: List<Repositorio>
): RecyclerView.Adapter<ListaRepositoriosAdapter.ViewHolder>() {

    private val repositorios = repositorios.toMutableList()

    class ViewHolder(private val binding: RepositorioItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun vincula(repositorio: Repositorio) {
            val nomeRepositorioTextView = binding.nomeRepositorioRecyclerView
            nomeRepositorioTextView.text = repositorio.nomeRepositorio
            val descricaoRepositorioTextView = binding.descricaoRepositorioRecyclerView
            descricaoRepositorioTextView.text = repositorio.descricaoRepositorio
            val nomeAutorTextView = binding.nomeAutorRecyclerView
            nomeAutorTextView.text = repositorio.nomeAutor
            val fotoAutorImageView = binding.fotoAutorImageView
//            fotoAutorImageView. = repositorio.fotoAutor
            val numeroStarsTextView = binding.numeroStarsRecyclerView
            numeroStarsTextView.text = repositorio.numeroStars
            val numeroForksTextView = binding.numeroForksRecyclerView
            numeroForksTextView.text = repositorio.numeroForks

//            binding.fotoAutorImageView.CarregaImagem(repositorio.fotoAutor)
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
    }

    override fun getItemCount(): Int = repositorios.size
    fun atualiza(repositorios: List<Repositorio>) {
//        this.produtos.clear()
        this.repositorios.addAll(repositorios)
        notifyDataSetChanged()
    }


}