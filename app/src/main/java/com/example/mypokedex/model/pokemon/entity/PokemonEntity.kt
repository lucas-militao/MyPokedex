package com.example.mypokedex.model.pokemon.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "nome") val name: String,
    @ColumnInfo(name = "altura") val height: Int,
    @ColumnInfo(name = "peso") val weight: Int,
    @ColumnInfo(name = "imagem") val sprite: String
): Parcelable