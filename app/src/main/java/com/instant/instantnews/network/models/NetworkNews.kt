package com.instant.instantnews.network.models

import com.instant.instantnews.ui.models.NewsModel
import com.squareup.moshi.Json

data class NetworkNews(
    @Json(name = "source")
    val source: NetworkSource,
    @Json(name = "author")
    val author: String?,
    @Json(name = "title")
    val title: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "urlToImage")
    val urlToImage: String,
    @Json(name = "publishedAt")
    val publishedAt: String,
    @Json(name = "content")
    val content: String
) {

    companion object {
        fun NetworkNews.toNewsModel(): NewsModel {
            return NewsModel(
                this.title,
                this.description,
                this.url,
                this.urlToImage
            )
        }
    }
}


