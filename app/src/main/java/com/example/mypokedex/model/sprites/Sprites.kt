package com.example.mypokedex.model.sprites

import com.squareup.moshi.Json

data class Sprites(
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "back_default")
    val backDefault: String?
)