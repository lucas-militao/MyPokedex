package com.example.mypokedex.util

import com.example.mypokedex.model.pokemon.dto.PokemonDto
import com.example.mypokedex.model.pokemon.entity.PokemonEntity
import com.example.mypokedex.model.pokemon.ui.Pokemon
import com.example.mypokedex.model.pokemontype.PokemonWithTypes
import com.example.mypokedex.model.type.entity.TypeEntity
import com.example.mypokedex.model.type.response.TypeResponse
import com.example.mypokedex.model.type.ui.Type


fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight,
        sprite = this.sprites?.frontDefault ?: "",
        types = arrayListOf()
    )
}

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight,
        sprite = this.sprite,
        types = arrayListOf()
    )
}

fun PokemonDto.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight,
        sprite = this.sprites?.frontDefault ?: ""
    )
}

fun PokemonWithTypes.toPokemon(): Pokemon {
    return Pokemon(
        id = this.pokemon.id,
        name = this.pokemon.name,
        height = this.pokemon.height,
        weight = this.pokemon.weight,
        sprite = this.pokemon.sprite,
        types = arrayListOf()
    )
}

fun TypeEntity.toType(): Type {
    return Type(
        id = this.id,
        name = this.name
    )
}

fun TypeResponse.toTypeEntity(): TypeEntity {
    return TypeEntity(
        id = this.id,
        name = this.name
    )
}