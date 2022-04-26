package com.instant.instantnews.network.models

import com.squareup.moshi.Json

data class NetworkNewsApiResponse(
    @Json(name = "status")
    val status : NetworkStatus,
    @Json(name = "totalResults")
    val totalResults : Int?,
    @Json(name = "articles")
    val articles : List<NetworkNews>,
    @Json(name = "code")
    val code : String?,
    @Json(name = "message")
    val message : String?,
)
