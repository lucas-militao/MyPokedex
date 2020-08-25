package com.example.mypokedex.model.pokemontype

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.mypokedex.model.pokemon.entity.PokemonEntity
import com.example.mypokedex.model.type.entity.TypeEntity


data class TypeWithPokemons(
    @Embedded
    val type: TypeEntity,
    @Relation(
        parentColumn = "id",
        entity = PokemonEntity::class,
        entityColumn = "id",
        associateBy = Junction(
            value = PokemonTypeEntity::class,
            parentColumn = "type_id",
            entityColumn = "pokemon_id"
        )
    )
    val pokemonEntity: List<PokemonEntity>
)