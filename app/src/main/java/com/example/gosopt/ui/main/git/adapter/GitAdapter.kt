package com.example.gosopt.ui.main.git.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.gosopt.data.model.Repo
import com.example.gosopt.databinding.ItemGitRepoBinding
import com.example.gosopt.util.ItemDiffCallback

class GitAdapter : ListAdapter<Repo, GitAdapter.GitViewHolder>(ItemDiffCallback(
    onItemsTheSame = { old, new -> old == new },
    onContentsTheSame = { old, new -> old == new }
)) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        val binding =
            ItemGitRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GitViewHolder(private val binding: ItemGitRepoBinding) : ViewHolder(binding.root) {
        fun bind(repo: Repo) {
            with(binding) {
                ivGitImage.load(repo.imageUrl)
                tvGitName.text = repo.name
                tvGitAuthor.text = repo.author
            }
        }
    }
}
