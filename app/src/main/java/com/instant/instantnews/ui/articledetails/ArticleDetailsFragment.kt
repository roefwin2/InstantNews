package com.instant.instantnews.ui.articledetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.instant.instantnews.R
import com.instant.instantnews.databinding.ArticleDetailsFragmentBinding
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

        binding.detailimage.load(args.newsDetails.urlToImage){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }

        binding.detailDescription.text = args.newsDetails.description

        binding.detailUrl.text = args.newsDetails.url
    }


}