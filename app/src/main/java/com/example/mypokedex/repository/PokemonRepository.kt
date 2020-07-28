package com.example.mypokedex.repository

import android.content.Context
import com.example.mypokedex.model.ListObjectResponse

class PokemonRepository(context: Context) {

    private val remote = PokemonRemoteRepository()
    private val local = PokemonLocalRepository(context)

    suspend fun getAll(offset: Int = 0, limit: Int = 10) : List<ListObjectResponse>? {
        return remote.getPokemons(offset, limit)
    }

}