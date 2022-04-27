package com.instant.instantnews.ui.articlelist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.instant.instantnews.databinding.InstantNewsItemBinding
import com.instant.instantnews.navigation.DetailsNews
import com.instant.instantnews.network.models.NetworkNews
import com.instant.instantnews.ui.models.NewsModel

class ArticleAdapter(val context: Context) : RecyclerView.Adapter<ArticleCardViewHolder>() {

    var data: List<NewsModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClickListener : ((DetailsNews) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = InstantNewsItemBinding.inflate(inflater, parent, false)
        return ArticleCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleCardViewHolder, position: Int) {
        val article = data[position]

        holder.binding.textView2.text = article.title

        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(context)
            .load(article.urlToImage)
            .centerInside()
            .apply(requestOptions)
            .into(holder.binding.imageView)

        holder.binding.root.setOnClickListener {
            val detailsNews = DetailsNews(article.title,article.urlToImage,article.description,article.url)
            onClickListener?.invoke(detailsNews)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}


data class ArticleCardViewHolder(val binding: InstantNewsItemBinding) :
    RecyclerView.ViewHolder(binding.root)