package com.example.mypokedex.usecase

import android.content.Context
import com.example.mypokedex.repository.pokemon.PokemonRepository

class SavePokemonsUseCase(context: Context) {

    private val pokemonRepository = PokemonRepository(context)

    suspend fun execute() {
        var next = pokemonRepository.getPokemonsFirstPageRemote()

        while (!next.isNullOrEmpty()) {
            next = pokemonRepository.getPokemonsNextPage(next)
        }
    }
}