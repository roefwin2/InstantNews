package com.instant.instantnews.ui.articlelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.instant.instantnews.databinding.ArticleListFragmentBinding
import com.instant.instantnews.network.models.NetworkNewsApiResponse
import com.instant.instantnews.ui.articlelist.adapter.ArticleAdapter
import com.instant.instantnews.utils.resource.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleListFragment : Fragment() {

    private var _binding: ArticleListFragmentBinding? = null
    private val binding: ArticleListFragmentBinding get() = _binding!!

    private lateinit var adapter: ArticleAdapter

    private val viewModel: ArticleListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ArticleAdapter()
        lifecycleScope.launchWhenResumed {
            viewModel.listScreenState.collect{
                processListScreenState(it)
            }
        }
        viewModel.fetchListData()
    }

    private fun processListScreenState(resource: Resource<NetworkNewsApiResponse>) {
        when(resource){
            is Resource.Error -> {}
            Resource.Loading -> {}
            is Resource.Success -> {
                resource.value.articles?.let { list ->
                    adapter.data = list.map { it.title }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArticleListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

}