package com.example.mypokedex.model.type.response

data class TypeResponse (
    val id: Int,
    val name: String,
    val pokemon: List<PokemonTypeResponse>
)