package com.example.mypokedex.util

import com.example.mypokedex.model.dto.PokemonDto
import com.example.mypokedex.model.entity.PokemonEntity
import com.example.mypokedex.model.ui.Pokemon

class Mapper {

    fun PokemonDto.toPokemon(): Pokemon {
        return Pokemon(
            id = this.id,
            name = this.name,
            height = this.height,
            weight = this.weight,
            sprite = this.sprites.frontDefault
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

}