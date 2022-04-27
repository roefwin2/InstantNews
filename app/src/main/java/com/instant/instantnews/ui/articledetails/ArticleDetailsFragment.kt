package com.instant.instantnews.ui.articledetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.instant.instantnews.R
import com.instant.instantnews.databinding.ArticleDetailsFragmentBinding
import com.instant.instantnews.databinding.ArticleListFragmentBinding
import com.instant.instantnews.ui.articlelist.ArticleListViewModel
import com.instant.instantnews.utils.resource.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsFragment : Fragment() {

    private var _binding: ArticleDetailsFragmentBinding? = null
    private val binding: ArticleDetailsFragmentBinding get() = _binding!!
    private val args : ArticleDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArticleDetailsFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
    }

    private fun setUI() {
        binding.detailTitle.text = args.newsDetails.title

        Glide.with(requireContext())
            .load(args.newsDetails.urlToImage)
            .into(binding.detailimage)

        binding.detailDescription.text = args.newsDetails.description

        binding.detailUrl.text = args.newsDetails.url
    }


}