package com.peter.pretest.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Articles(
    @Json(name = "status") var status: String? = null,
    @Json(name = "totalResults") var totalResults: Int? = null,
    @Json(name = "articles") var articles: List<News>? = null,
    val error: String? = null
) : Parcelable {}
