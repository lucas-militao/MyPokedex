package com.example.mypokedex.model

import com.example.mypokedex.model.PokemonResponse

data class AllPokemonsResponse (
    val count: Int,
    val next: String,
    val previous: String,
    val results: ArrayList<PokemonResponse>
)