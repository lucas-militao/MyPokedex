package com.example.mypokedex.model.pokemon.ui

import android.os.Parcelable
import com.example.mypokedex.model.type.ui.Type
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprite: String,
    val types: ArrayList<Type>
): Parcelable