package com.example.mypokedex.network

import com.example.mypokedex.model.ListObjectResponse
import com.example.mypokedex.model.ListResponse
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.model.PokemonSprites
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://pokeapi.co/api/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface PokemonApiService {
    @GET("pokemon")
    fun getList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int): Deferred<ListResponse<ListObjectResponse>>

    @GET("pokemon/{param}/")
    fun getPokemon(@Path("param") param: String): Deferred<Pokemon>
}

object PokemonApi {
    val retrofitService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}