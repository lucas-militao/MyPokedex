package com.example.mypokedex.model.type

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Type(
    val name: String,
    val url: String
): Parcelable