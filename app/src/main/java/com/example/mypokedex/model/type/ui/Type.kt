package com.example.mypokedex.model.type.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Type(
    val id: Int,
    val name: String
): Parcelable