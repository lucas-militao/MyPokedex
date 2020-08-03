package com.example.mypokedex.model.response

data class ListResponse<T> (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>?
)