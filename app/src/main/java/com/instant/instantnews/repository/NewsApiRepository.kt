package com.instant.instantnews.repository

import com.instant.instantnews.network.api.NewsApi
import com.instant.instantnews.network.models.NetworkNewsApiResponse
import com.instant.instantnews.network.models.NetworkStatus
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class NewsApiRepository@Inject constructor(private val newsApi: NewsApi){

    suspend fun getArticles() = flow {
        try {
            val result =  newsApi.getEverything()
            emit(result.totalResults.toString())
        }catch (e:Exception){
             emit(e.toString())
        }

    }
}