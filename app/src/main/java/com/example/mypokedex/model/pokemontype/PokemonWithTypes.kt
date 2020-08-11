package com.example.mypokedex.model.pokemontype

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.mypokedex.model.pokemon.entity.PokemonEntity
import com.example.mypokedex.model.type.entity.TypeEntity

data class PokemonWithTypes(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "type_id",
        entityColumn = "pokemon_id",
        associateBy = Junction(PokemonTypeEntity::class)
    )
    val type: List<TypeEntity>
)