package com.example.mypokedex.model.dto

import com.example.mypokedex.model.PokemonSprites

data class PokemonDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: PokemonSprites
)