package com.example.gosopt.ui.main.git

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        adapter.submitList(viewModel.repoList)
    }

    fun scrollToTop() {
        binding.rvGit?.scrollToPosition(0)
    }
}