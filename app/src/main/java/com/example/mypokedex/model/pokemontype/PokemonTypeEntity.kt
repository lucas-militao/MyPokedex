package com.example.mypokedex.model.pokemontype

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.mypokedex.model.pokemon.entity.PokemonEntity
import com.example.mypokedex.model.type.entity.TypeEntity

@Entity(tableName = "pokemon_type",

    primaryKeys = ["pokemon_id", "type_id"],

    foreignKeys = [ForeignKey(entity = PokemonEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("pokemon_id"),
        onDelete = ForeignKey.CASCADE),

        ForeignKey(entity = TypeEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("type_id"),
        onDelete = ForeignKey.CASCADE)]
)
data class PokemonTypeEntity(
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "type_id") val typeId: Int
)