package com.instant.instantnews.ui.articlelist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.instant.instantnews.R
import com.instant.instantnews.databinding.InstantNewsItemBinding
import com.instant.instantnews.navigation.DetailsNews
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

        val imgUri = article.urlToImage.toUri().buildUpon().scheme("https").build()
        holder.binding.imageView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }

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