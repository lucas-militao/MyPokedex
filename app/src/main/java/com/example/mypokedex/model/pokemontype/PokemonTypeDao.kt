package com.example.mypokedex.model.pokemontype

import androidx.room.*
import com.example.mypokedex.model.type.dao.TypeDao

@Dao
interface PokemonTypeDao {

    @Transaction
    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun searchPokemon(id: Int): List<PokemonWithTypes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonType: PokemonTypeEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(pokemonType: PokemonTypeEntity): Int

    @Transaction
    suspend fun insertOrUpdate(pokemonType: PokemonTypeEntity): Long {
        var dataId = insert(pokemonType)
        if (dataId == TypeDao.DATA_NOT_INSERTED) dataId = update(pokemonType).toLong()
        return dataId
    }
}