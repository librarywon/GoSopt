package com.example.gosopt.ui.main.git.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.gosopt.data.model.GitItem
import com.example.gosopt.databinding.ItemGitRepoBinding
import com.example.gosopt.databinding.ItemGitTitleBinding
import com.example.gosopt.util.ItemDiffCallback

class GitAdapter : ListAdapter<GitItem, ViewHolder>(ItemDiffCallback(
    onItemsTheSame = { old, new -> old == new },
    onContentsTheSame = { old, new -> old == new }
)) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            VIEW_TYPE_TITLE -> {
                val binding = ItemGitTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TitleViewHolder(binding)
            }
            VIEW_TYPE_REPO -> {
                val binding = ItemGitRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GitViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is GitItem.Title -> VIEW_TYPE_TITLE
            is GitItem.Repo -> VIEW_TYPE_REPO
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is GitItem.Title -> (holder as TitleViewHolder).bind(item)
            is GitItem.Repo -> (holder as GitViewHolder).bind(item)
        }
    }

    class TitleViewHolder(private val binding: ItemGitTitleBinding) : ViewHolder(binding.root) {
        fun bind(title: GitItem.Title) {
            binding.tvGitTitle.text = title.text
        }
    }

    class GitViewHolder(private val binding: ItemGitRepoBinding) : ViewHolder(binding.root) {
        fun bind(repo: GitItem.Repo) {
            with(binding) {
                ivGitImage.load(repo.imageUrl)
                tvGitName.text = repo.name
                tvGitAuthor.text = repo.author
            }
        }
    }

    companion object {
        const val VIEW_TYPE_TITLE = 0
        const val VIEW_TYPE_REPO =1
    }
}
