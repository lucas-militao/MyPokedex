package com.example.mypokedex.model.type.response

import android.os.Parcelable
import com.example.mypokedex.model.type.dto.TypeDto
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Types(
    val slot: Int,
    val type: TypeDto
): Parcelable