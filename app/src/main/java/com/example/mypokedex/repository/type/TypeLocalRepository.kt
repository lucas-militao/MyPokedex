package com.example.mypokedex.repository.type

import android.content.Context
import com.example.mypokedex.database.PokemonDatabase
import com.example.mypokedex.model.type.entity.TypeEntity

class TypeLocalRepository(context: Context) {

    private val typeDao = PokemonDatabase.getDatabase(context).typeDao()

    suspend fun insert(type: TypeEntity) {
        typeDao.insertOrUpdate(type)
    }

    suspend fun insert(data: List<TypeEntity>) {
        typeDao.insertOrUpdate(data)
    }

}