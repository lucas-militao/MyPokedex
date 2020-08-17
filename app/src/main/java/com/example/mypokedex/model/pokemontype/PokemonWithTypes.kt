package com.example.mypokedex.model.pokemontype

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.mypokedex.model.pokemon.entity.PokemonEntity
import com.example.mypokedex.model.type.entity.TypeEntity

data class PokemonWithTypes(
    @Embedded
    val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "id",
        entity = TypeEntity::class,
        entityColumn = "id",
        associateBy = Junction(
            value = PokemonTypeEntity::class,
            parentColumn = "pokemon_id",
            entityColumn = "type_id"
        )
    )
    val type: List<TypeEntity>
)