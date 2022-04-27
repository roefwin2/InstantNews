package com.instant.instantnews.network.models

import com.squareup.moshi.Json

data class NetworkSource(
    @Json(name = "id")
    val id : String?,
    @Json(name = "name")
    val name : String
)
