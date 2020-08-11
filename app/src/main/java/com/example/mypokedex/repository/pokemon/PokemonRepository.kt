package com.example.mypokedex.repository.pokemon

import android.content.Context
import com.example.mypokedex.model.pokemon.entity.PokemonEntity
import com.example.mypokedex.util.toPokemonEntity

class PokemonRepository(context: Context) {

    private val remote = PokemonRemoteRepository()
    private val local = PokemonLocalRepository(context)

    suspend fun getPokemonsRemote(offset: Int = 0, limit: Int = 10, url: String = "") : String? {
        val pokemons = if (url.isEmpty()) remote.getPokemons(offset, limit) else remote.getNextList(url)
        val pokemonsEntity = arrayListOf<PokemonEntity>()

        for (pokemon in pokemons!!.results!!) {
           pokemonsEntity.add(remote.searchPokemonByNameOrId(pokemon.name)!!.toPokemonEntity())
        }

        local.insert(pokemonsEntity)

        return pokemons.next
    }


}