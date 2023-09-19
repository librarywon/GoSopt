package com.example.gosopt.ui.main.git.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.gosopt.R
import com.example.gosopt.data.model.GitItem
import com.example.gosopt.databinding.ItemGitButtonBinding
import com.example.gosopt.databinding.ItemGitRepoBinding
import com.example.gosopt.databinding.ItemGitTitleBinding
import com.example.gosopt.util.ItemDiffCallback

class GitAdapter : ListAdapter<GitItem, ViewHolder>(ItemDiffCallback(
    onItemsTheSame = { old, new -> old == new },
    onContentsTheSame = { old, new -> old == new }
)) {
    var tracker: SelectionTracker<Long>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            VIEW_TYPE_TITLE -> {
                val binding =
                    ItemGitTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TitleViewHolder(binding)
            }

            VIEW_TYPE_REPO -> {
                val binding =
                    ItemGitRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GitViewHolder(binding)
            }

            VIEW_TYPE_BUTTON -> {
                val binding =
                    ItemGitButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ButtonViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is GitItem.Title -> VIEW_TYPE_TITLE
            is GitItem.Repo -> VIEW_TYPE_REPO
            is GitItem.Button -> VIEW_TYPE_BUTTON
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is GitItem.Title -> (holder as TitleViewHolder).bind(item)
            is GitItem.Repo -> (holder as GitViewHolder).bind(item)
            is GitItem.Button -> (holder as ButtonViewHolder).bind(item)
        }
    }

    class TitleViewHolder(private val binding: ItemGitTitleBinding) : ViewHolder(binding.root) {
        fun bind(title: GitItem.Title) {
            binding.tvGitTitle.text = title.text
        }
    }

    inner class GitViewHolder(private val binding: ItemGitRepoBinding) : ViewHolder(binding.root) {

        init {
            // 항목 클릭 시 선택 상태 토글
            itemView.setOnClickListener {
                val item = getItem(adapterPosition)
                if (item is GitItem.Repo) {
                    val itemId = item.id
                    if (tracker?.isSelected(itemId) == true) {
                        tracker?.deselect(itemId)
                    } else {
                        tracker?.select(itemId)
                    }
                }
            }
        }

        fun bind(repo: GitItem.Repo) {
            with(binding) {
                ivGitImage.load(repo.imageUrl)
                tvGitName.text = repo.name
                tvGitAuthor.text = repo.author

                // 선택 상태 바인딩
                if (tracker?.isSelected(repo.id) == true) {
                    itemView.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.transparent_pink
                        )
                    )
                } else {
                    itemView.background = null
                }
            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long? =
                    (getItem(adapterPosition) as? GitItem.Repo)?.id
            }

    }

    inner class ButtonViewHolder(private val binding: ItemGitButtonBinding) :
        ViewHolder(binding.root) {
        fun bind(button: GitItem.Button) {
            with(binding) {
                btnGitButton.text = button.text

                btnGitButton.setOnClickListener {
                    deleteSelectedItems()
                }
            }
        }
    }

    class GitItemKeyProvider(private val adapter: GitAdapter) :
        ItemKeyProvider<Long>(SCOPE_MAPPED) {
        override fun getKey(position: Int): Long? {
            return (adapter.getItem(position) as? GitItem.Repo)?.id
        }

        override fun getPosition(key: Long): Int {
            return adapter.currentList.indexOfFirst { (it as? GitItem.Repo)?.id == key }
        }
    }

    class GitItemDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
        override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
            val view = recyclerView.findChildViewUnder(e.x, e.y)
            if (view != null) {
                val holder = recyclerView.getChildViewHolder(view)
                if (holder is GitAdapter.GitViewHolder) {
                    return holder.getItemDetails()
                }
            }
            return null
        }
    }


    private fun deleteSelectedItems() {
        // 선택된 항목의 ID 목록 가져오기
        val selectedItems = tracker?.selection?.toList()

        // 선택된 항목을 데이터 목록에서 제거
        if (!selectedItems.isNullOrEmpty()) {
            val currentList = currentList.toMutableList()
            val itemsToRemove = currentList.filter { it is GitItem.Repo && it.id in selectedItems }
            currentList.removeAll(itemsToRemove)

            // 디버깅을 위한 로그 메시지
            Log.d("GitAdapter", "Selected items: $selectedItems")
            Log.d("GitAdapter", "Items to remove: $itemsToRemove")
            Log.d("GitAdapter", "Updated list: $currentList")

            submitList(currentList) {
                notifyDataSetChanged()  // 데이터 변경 알림
            }
            // 선택 상태 초기화
            tracker?.clearSelection()
        } else {
            // 선택된 항목이 없는 경우 디버깅을 위한 로그 메시지
            Log.d("GitAdapter", "No items selected")
        }
    }







    fun initializeTracker(recyclerView: RecyclerView) {
        if (tracker == null) {
            tracker = SelectionTracker.Builder(
                "gitSelection",
                recyclerView,
                GitItemKeyProvider(this),
                GitItemDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage()
            ).withSelectionPredicate(
                SelectionPredicates.createSelectAnything()
            ).build()
        }
    }


    companion object {
        const val VIEW_TYPE_TITLE = 0
        const val VIEW_TYPE_REPO = 1
        const val VIEW_TYPE_BUTTON = 2
    }

}
