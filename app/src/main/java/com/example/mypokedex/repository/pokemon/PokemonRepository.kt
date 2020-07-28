package com.example.mypokedex.repository.pokemon

import android.content.Context
import com.example.mypokedex.model.pokemon.dto.PokemonDto

class PokemonRepository(context: Context) {

    private val remote = PokemonRemoteRepository()
    private val local = PokemonLocalRepository(context)

    suspend fun getAll(offset: Int, limit: Int): List<PokemonDto>? {
        val list = remote.getPokemons(offset, limit)
        val pokemons = arrayListOf<PokemonDto>()

        for (result in list!!) {
            val pokemon = remote.searchPokemonByNameOrId(result.name)
            pokemons.add(pokemon!!)
        }

        return pokemons
    }

    suspend fun getNext(url: String): List<PokemonDto>? {
        val list = remote.getNextList(url)
        val pokemons = arrayListOf<PokemonDto>()

        for (result in list!!) {
            val pokemon = remote.searchPokemonByNameOrId(result.name)
            pokemons.add(pokemon!!)
        }

        return pokemons
    }

}