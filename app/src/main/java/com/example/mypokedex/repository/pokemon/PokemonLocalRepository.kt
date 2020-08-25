package com.example.mypokedex.repository.pokemon

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mypokedex.database.PokemonDatabase
import com.example.mypokedex.model.pokemon.entity.PokemonEntity
import com.example.mypokedex.model.pokemontype.PokemonWithTypes
import com.example.mypokedex.model.pokemontype.TypeWithPokemons

class PokemonLocalRepository(context: Context) {

    private val pokemonDao = PokemonDatabase.getDatabase(context).pokemonDao()

    suspend fun insert(pokemonEntity: PokemonEntity) {
        pokemonDao.insert(pokemonEntity)
    }

    suspend fun insert(data: List<PokemonEntity>) {
        pokemonDao.insertOrUpdate(data)
    }

    fun getAll(): LiveData<List<PokemonWithTypes>> {
        return pokemonDao.getAll()
    }

    suspend fun getPokemonsByType(name: String): TypeWithPokemons {
        return pokemonDao.searchPokemonsByType(name)
    }

}