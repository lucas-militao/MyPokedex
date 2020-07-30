package com.example.mypokedex.model.pokemon.dto

import com.example.mypokedex.model.sprites.Sprites
import com.example.mypokedex.model.type.Type
import com.example.mypokedex.model.type.Types

data class PokemonDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<Types>
)