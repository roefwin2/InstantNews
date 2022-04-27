package com.instant.instantnews.repository

import com.instant.instantnews.network.api.NewsApi
import com.instant.instantnews.network.models.NetworkNewsApiResponse
import com.instant.instantnews.network.models.NetworkStatus
import com.instant.instantnews.utils.resource.Resource
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class NewsApiRepository @Inject constructor(private val newsApi: NewsApi) {

    suspend fun getArticles() = flow {
        emit(Resource.Loading)
        try {
            val result = newsApi.getEverything()
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }

    }
}