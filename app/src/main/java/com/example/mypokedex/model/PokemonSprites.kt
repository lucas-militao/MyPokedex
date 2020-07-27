package com.example.mypokedex.model

import com.squareup.moshi.Json

data class PokemonSprites(
    @Json(name = "front_default")
    private val frontDefault: String,
    @Json(name = "back_default")
    private val backDefault: String
)