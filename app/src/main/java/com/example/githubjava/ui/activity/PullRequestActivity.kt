package com.example.githubjava.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubjava.databinding.ActivityHomeBinding
import com.example.githubjava.databinding.ActivityPullRequestBinding

class PullRequestActivity : AppCompatActivity() {

    private val binding by lazy {
       ActivityPullRequestBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}