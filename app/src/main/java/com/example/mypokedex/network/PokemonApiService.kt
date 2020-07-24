package com.example.mypokedex.network

import com.example.mypokedex.model.AllPokemonsResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://pokeapi.co/api/v2/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface PokemonApiService {
    @GET("pokemon")
    fun getAllPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int): Call<String>
}

object PokemonApi {
    val retrofitService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}