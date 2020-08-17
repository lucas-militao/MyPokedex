package com.example.mypokedex.model.type.dao

import androidx.room.*
import com.example.mypokedex.model.type.entity.TypeEntity

@Dao
interface TypeDao {
    companion object {
        const val DATA_NOT_INSERTED = -1L
    }

    @Query("SELECT * FROM type")
    fun getAll(): List<TypeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(type: TypeEntity): Long

    @Transaction
    suspend fun insert(data: List<TypeEntity>) {
        data.forEach { if (insert(it) == DATA_NOT_INSERTED) update(it) }
    }

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(type: TypeEntity): Int

    @Transaction
    suspend fun insertOrUpdate(data: List<TypeEntity>) {
        data.forEach { if (insert(it) == -1L)  update(it) }
    }

    @Transaction
    suspend fun insertOrUpdate(type: TypeEntity): Long {
        var dataId = insert(type)
        if (dataId == DATA_NOT_INSERTED) dataId = update(type).toLong()
        return dataId
    }
}