package com.example.githubjava.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjava.databinding.PullRequestItemBinding
import com.example.githubjava.data.models.PullRequests

class ListaPullRequestsAdapter (
    private val context: Context,
    pullRequests: List<PullRequests>
): ListAdapter<PullRequests,ListaPullRequestsAdapter.ViewHolder>(DiffUtilCallBack) {

    private val pullRequests = pullRequests.toMutableList()

    class ViewHolder(private val binding: PullRequestItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun vincula(pullRequests: PullRequests) {

            val tituloPullRequestsRecyclerView = binding.tituloPullRequestsRecyclerView
            tituloPullRequestsRecyclerView.text = pullRequests.tituloPullRequests
            val bodyPullRequestRecyclerView = binding.bodyPullRequestRecyclerView
            bodyPullRequestRecyclerView.text = pullRequests.bodyPullRequest
            val dataRecyclerView = binding.dataRecyclerView
            dataRecyclerView.text = pullRequests.dataPullRequests
            val nomeAutorRecyclerView = binding.nomeAutorRecyclerView
            nomeAutorRecyclerView.text = pullRequests.nomeAutorPullrequests


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = PullRequestItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pullRequest = getItem(position)
        holder.vincula(pullRequest)
    }


}

private object DiffUtilCallBack : DiffUtil.ItemCallback<PullRequests>(){
    override fun areItemsTheSame(oldItem: PullRequests, newItem: PullRequests): Boolean {
        return oldItem.tituloPullRequests == newItem.tituloPullRequests
    }

    override fun areContentsTheSame(oldItem: PullRequests, newItem: PullRequests): Boolean {
        return oldItem == newItem
    }

}