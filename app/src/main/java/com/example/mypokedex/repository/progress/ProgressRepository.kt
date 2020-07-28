package com.example.mypokedex.repository.progress

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mypokedex.database.PokemonDatabase
import com.example.mypokedex.model.progress.entity.ProgressEntity

class ProgressRepository(context: Context) {

    private val progressDao = PokemonDatabase.getDatabase(context).progressDao()

    fun get(): LiveData<ProgressEntity> {
        return progressDao.get()
    }

    fun insert(progressEntity: ProgressEntity) {
        progressDao.insert(progressEntity)
    }

}