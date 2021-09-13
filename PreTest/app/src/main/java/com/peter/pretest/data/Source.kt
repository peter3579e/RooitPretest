package com.peter.pretest.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Source(
 var id: String? = null,
 var name: String? = null,
) : Parcelable {}