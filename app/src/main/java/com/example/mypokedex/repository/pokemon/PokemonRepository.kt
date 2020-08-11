package com.example.mypokedex.repository.pokemon

import android.content.Context
import com.example.mypokedex.model.pokemon.entity.PokemonEntity
import com.example.mypokedex.util.toPokemonEntity

class PokemonRepository(context: Context) {

    private val remote = PokemonRemoteRepository()
    private val local = PokemonLocalRepository(context)

    suspend fun getPokemonsFirstPageRemote(offset: Int = 0, limit: Int = 10) : String? {
        val pokemons = remote.getPokemons(offset, limit)
        val pokemonsEntity = arrayListOf<PokemonEntity>()

        for (pokemon in pokemons!!.results!!) {
           pokemonsEntity.add(remote.searchPokemonByNameOrId(pokemon.name)!!.toPokemonEntity())
        }

        local.insert(pokemonsEntity)

        return pokemons.next
    }

    suspend fun getPokemonsNextPage(url: String): String? {
        val pokemons = remote.getNextList(url)
        val pokemonsEntity = arrayListOf<PokemonEntity>()

        for (pokemon in pokemons!!.results!!) {
            pokemonsEntity.add(remote.searchPokemonByNameOrId(pokemon.name)!!.toPokemonEntity())
        }

        local.insert(pokemonsEntity)

        return pokemons.next
    }


}