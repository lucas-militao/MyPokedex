package com.example.mypokedex.model

import com.squareup.moshi.Json

data class PokemonSprites(
    @Json(name = "front_default")
    val frontDefault: String,
    @Json(name = "back_default")
    val backDefault: String
)