package com.example.mypokedex.model.type

data class TypeResponse<T>(
    val id: Int,
    val name: String,
    val pokemon: List<PokemonTypeResponse>
)