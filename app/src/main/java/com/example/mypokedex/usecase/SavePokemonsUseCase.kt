package com.example.mypokedex.usecase

import android.content.Context
import com.example.mypokedex.repository.pokemon.PokemonRepository
import com.example.mypokedex.repository.progress.ProgressRepository

class SavePokemonsUseCase(context: Context) {

    private val progressRepository = ProgressRepository(context)
    private val pokemonRepository = PokemonRepository(context)

    suspend fun execute() {
        //TODO
    }

}