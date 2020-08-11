package com.example.mypokedex.model.pokemon.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mypokedex.model.pokemon.entity.PokemonEntity

@Dao
interface PokemonDao {

    companion object {
        const val DATA_NOT_INSERTED = -1L
    }

    @Query("SELECT * FROM pokemon ORDER BY id ASC")
    fun getAll(): LiveData<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonEntity: PokemonEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(pokemonEntity: PokemonEntity)

    @Transaction
    suspend fun insertOrUpdate(data: List<PokemonEntity>) {
        data.forEach { if (insert(it) == DATA_NOT_INSERTED)  update(it) }
    }

}