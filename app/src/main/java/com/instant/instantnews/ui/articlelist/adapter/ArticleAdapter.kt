package com.instant.instantnews.ui.articlelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instant.instantnews.databinding.InstantNewsItemBinding

class ArticleAdapter() : RecyclerView.Adapter<ArticleCardViewHolder>() {
    var data: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = InstantNewsItemBinding.inflate(inflater, parent, false)
        return ArticleCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleCardViewHolder, position: Int) {
        val article = data[position]
        holder.binding.textView2.text = article
    }

    override fun getItemCount(): Int {
        return data.size
    }
}


data class ArticleCardViewHolder(val binding: InstantNewsItemBinding) :
    RecyclerView.ViewHolder(binding.root)