package com.example.mypokedex.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "nome") val name: String,
    @ColumnInfo(name = "altura") val height: Int,
    @ColumnInfo(name = "peso") val weight: Int,
    @ColumnInfo(name = "tipo") val type: String,
    @ColumnInfo(name = "imagem") val sprite: String
)