package com.example.mypokedex.usecase

import android.content.Context
import com.example.mypokedex.repository.pokemon.PokemonRepository
import java.lang.Exception

class SavePokemonTypesUseCase(context: Context) {

    private val pokemonRepository = PokemonRepository(context)

    suspend fun execute() {
        pokemonRepository.getTypes()
    }
}