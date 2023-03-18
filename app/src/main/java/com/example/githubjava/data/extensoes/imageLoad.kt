package com.example.githubjava.data.extensoes

import android.widget.ImageView
import coil.load
import com.example.githubjava.R
import okhttp3.HttpUrl

fun ImageView.CarregaImagem(url: String? = null){
    load(url){
        fallback(R.drawable.avatar_icon)
        error(R.drawable.avatar_icon)
        placeholder(R.drawable.avatar_icon)
    }
}
