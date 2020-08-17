package com.example.mypokedex.repository.type

import android.util.Log
import com.example.mypokedex.model.response.ListResponse
import com.example.mypokedex.network.PokemonApiService
import com.example.mypokedex.network.retrofit
import com.example.mypokedex.model.type.dto.TypeDto
import com.example.mypokedex.model.type.response.PokemonTypeResponse
import com.example.mypokedex.model.type.response.TypeResponse
import java.lang.Exception

class TypeRemoteRepository {

    private val retrofitService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }

    suspend fun getTypes(): ListResponse<TypeDto>? {
        val getTypesDeferred = retrofitService.getPokemonTypes()
        return try {
            getTypesDeferred.await()
        } catch (e: Exception) {
            Log.e("GET TYPES ERROR", "Failure: ${e.message}")
            null
        }
    }

    suspend fun getType(name: String): TypeResponse? {
        val getTypeDeferred = retrofitService.searchPokemonsByType(name)

        return try {
            getTypeDeferred.await()
        } catch (e: Exception) {
            Log.e("GET TYPES ERROR", "Failure: ${e.message}")
            null
        }
    }

}