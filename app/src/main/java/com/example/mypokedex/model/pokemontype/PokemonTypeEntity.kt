package com.example.mypokedex.model.pokemontype

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "pokemon_type",
    primaryKeys = ["pokemon_id", "type_id"])
data class PokemonTypeEntity(
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "type_id") val typeId: Int
)