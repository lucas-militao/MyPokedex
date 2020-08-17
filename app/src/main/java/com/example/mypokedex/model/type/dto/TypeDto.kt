package com.example.mypokedex.model.type.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TypeDto(
    val name: String,
    val url: String
): Parcelable