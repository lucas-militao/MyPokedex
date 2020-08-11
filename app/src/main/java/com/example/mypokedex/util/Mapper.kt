package com.example.mypokedex.util

import com.example.mypokedex.model.pokemon.dto.PokemonDto
import com.example.mypokedex.model.pokemon.entity.PokemonEntity
import com.example.mypokedex.model.pokemon.ui.Pokemon



fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight,
        sprite = this.sprites?.frontDefault ?: ""
    )
}

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight,
        sprite = this.sprite
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