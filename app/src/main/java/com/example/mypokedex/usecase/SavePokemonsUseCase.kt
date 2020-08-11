package com.example.mypokedex.usecase

import android.content.Context
import com.example.mypokedex.repository.pokemon.PokemonRepository
import com.example.mypokedex.repository.progress.ProgressRepository

class SavePokemonsUseCase(context: Context) {

    private val pokemonRepository = PokemonRepository(context)

    suspend fun execute() {
        var next = pokemonRepository.getPokemonsRemote()

        while (!next.isNullOrEmpty()) {
            next = pokemonRepository.getPokemonsRemote(0,0, next)
        }
    }
}