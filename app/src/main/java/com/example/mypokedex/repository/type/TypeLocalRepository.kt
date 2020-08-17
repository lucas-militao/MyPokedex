package com.example.mypokedex.repository.type

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mypokedex.database.PokemonDatabase
import com.example.mypokedex.model.type.entity.TypeEntity

class TypeLocalRepository(context: Context) {

    private val typeDao = PokemonDatabase.getDatabase(context).typeDao()

    suspend fun getTypeByName(param: String): TypeEntity {
        return typeDao.searchType(param)
    }

    suspend fun insert(type: TypeEntity) {
        typeDao.insertOrUpdate(type)
    }

    suspend fun insert(data: List<TypeEntity>) {
        typeDao.insertOrUpdate(data)
    }

    fun getAll(): LiveData<List<TypeEntity>> {
        return typeDao.getAllLiveData()
    }

}