package com.instant.instantnews.repository

import com.instant.instantnews.network.api.NewsApi
import com.instant.instantnews.network.models.NetworkNews
import com.instant.instantnews.ui.models.NewsModel
import com.instant.instantnews.utils.resource.Resource
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class NewsApiRepository @Inject constructor(private val newsApi: NewsApi) {

    suspend fun getArticles() = flow {
        emit(Resource.Loading)
        try {
            val result = newsApi.getEverything()
            if (result.status == "ok") {
                result.articles?.let {
                    val model = transformNetworkToModel(it)
                    emit(Resource.Success(model))
                }
            } else {
                emit(result.message?.let { Resource.Error(it) })
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }

    }

    private fun transformNetworkToModel(list: List<NetworkNews>): List<NewsModel> {
        return list.map { NewsModel(it.title, it.description, it.url, it.urlToImage) }
    }
}