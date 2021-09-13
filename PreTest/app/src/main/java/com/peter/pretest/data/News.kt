package com.peter.pretest.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    var source: Source? = null,
    var title: String? = null,
    var urlToImage: String? = null,
    val error: String? = null
) : Parcelable {}