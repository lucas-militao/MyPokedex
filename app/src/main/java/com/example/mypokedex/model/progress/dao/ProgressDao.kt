package com.example.mypokedex.model.progress.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mypokedex.model.progress.entity.ProgressEntity

@Dao
interface ProgressDao {

    @Query("SELECT * FROM progresso WHERE id = 1")
    fun get(): LiveData<ProgressEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(progressEntity: ProgressEntity)

}