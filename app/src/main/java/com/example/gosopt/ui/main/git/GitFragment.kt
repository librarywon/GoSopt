package com.example.gosopt.ui.main.git

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gosopt.databinding.FragmentGitBinding
import com.example.gosopt.ui.main.git.adapter.GitAdapter

class GitFragment : Fragment() {
    private var _binding: FragmentGitBinding? = null
    private val binding: FragmentGitBinding
        get() = requireNotNull(_binding) { "binding is null" }
    private val viewModel: GitViewModel by lazy {
        GitViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = GitAdapter()
        binding.rvGit.layoutManager = LinearLayoutManager(requireContext())
        binding.rvGit.adapter = adapter

        // repoList의 변경을 관찰하고 어댑터에 새로운 목록을 제출
        viewModel.repoList.observe(viewLifecycleOwner, Observer { newList ->
            adapter.submitList(newList)
        })

        // SelectionTracker 초기화 및 GitAdapter에 설정
        adapter.initializeTracker(binding.rvGit)
    }


    fun scrollToTop() {
        binding.rvGit?.smoothScrollToPosition(0)
    }
}