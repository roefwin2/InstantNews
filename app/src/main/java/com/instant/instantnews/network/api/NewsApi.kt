package com.instant.instantnews.network.api

import com.instant.instantnews.network.models.NetworkNews
import com.instant.instantnews.network.models.NetworkNewsApiResponse
import com.instant.instantnews.utils.Constants
import retrofit2.http.GET

interface NewsApi {
    @GET(Constants.NEWS_API_ENDPOINT)
    suspend fun getEverything() : NetworkNewsApiResponse
}