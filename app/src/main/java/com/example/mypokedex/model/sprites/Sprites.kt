package com.example.mypokedex.model.sprites

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sprites(
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "back_default")
    val backDefault: String?
): Parcelable