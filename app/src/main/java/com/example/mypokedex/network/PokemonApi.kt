package com.example.mypokedex.network

import com.example.mypokedex.model.pokemon.dto.PokemonDto
import com.example.mypokedex.model.response.ListObjectResponse
import com.example.mypokedex.model.response.ListResponse
import com.example.mypokedex.model.type.dto.TypeDto
import com.example.mypokedex.model.type.response.TypeResponse
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
    fun getNextListByUrl(
        @Url url: String
    ): Deferred<ListResponse<ListObjectResponse>>

    @GET(Paths.Pokemon + "{param}/")
    fun getPokemonByNameOrId(
        @Path("param") param: String
    ): Deferred<PokemonDto>

    @GET(Paths.PokemonTypes)
    fun getPokemonTypes(): Deferred<ListResponse<TypeDto>>

    @GET(Paths.PokemonTypes + "{param}/")
    fun searchPokemonsByType(
        @Path("param") param: String
    ): Deferred<TypeResponse>

}