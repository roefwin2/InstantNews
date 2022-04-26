package com.instant.instantnews.repository

import com.instant.instantnews.network.api.NewsApi
import javax.inject.Inject

class NewsApiRepository@Inject constructor(private val newsApi: NewsApi){

}