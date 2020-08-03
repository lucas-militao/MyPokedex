package com.example.mypokedex.network

import com.example.mypokedex.model.response.ListObjectResponse
import com.example.mypokedex.model.response.ListResponse
import com.example.mypokedex.model.pokemon.dto.PokemonDto
import com.example.mypokedex.model.type.Type
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApiService {
    @GET(Paths.Pokemon)
    fun getList(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int): Deferred<ListResponse<ListObjectResponse>>

    @GET
    fun getNextList(@Url url: String): Deferred<ListResponse<ListObjectResponse>>

    @GET(Paths.Pokemon + "{param}/")
    fun getPokemonByNameOrId(@Path("param") param: String): Deferred<PokemonDto>

    @GET(Paths.PokemonTypes)
    fun getPokemonTypes(): Deferred<ListResponse<Type>>
}