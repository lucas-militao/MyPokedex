package com.example.mypokedex.network

import com.example.mypokedex.model.ListObjectResponse
import com.example.mypokedex.model.ListResponse
import com.example.mypokedex.model.pokemon.dto.PokemonDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApiService {
    @GET(Paths.getPokemons)
    fun getList(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int): Deferred<ListResponse<ListObjectResponse>>

    @GET
    fun getNextList(@Url url: String): Deferred<ListResponse<ListObjectResponse>>

    @GET(Paths.getPokemons + "{param}/")
    fun getPokemonByNameOrId(@Path("param") param: String): Deferred<PokemonDto>
}