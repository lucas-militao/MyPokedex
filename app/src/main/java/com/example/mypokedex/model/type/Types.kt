package com.example.mypokedex.model.type

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Types(
    val slot: Int,
    val type: Type
): Parcelable