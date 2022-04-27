package com.instant.instantnews.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsNews(
    val title : String,
    val urlToImage : String,
    val description : String,
    val url :String
) : Parcelable