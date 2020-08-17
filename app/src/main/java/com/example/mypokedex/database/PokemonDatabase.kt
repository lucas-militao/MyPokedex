package com.example.mypokedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mypokedex.model.pokemon.dao.PokemonDao
import com.example.mypokedex.model.pokemon.entity.PokemonEntity
import com.example.mypokedex.model.pokemontype.PokemonTypeDao
import com.example.mypokedex.model.pokemontype.PokemonTypeEntity
import com.example.mypokedex.model.progress.dao.ProgressDao
import com.example.mypokedex.model.progress.entity.ProgressEntity
import com.example.mypokedex.model.type.dao.TypeDao
import com.example.mypokedex.model.type.entity.TypeEntity

@Database(
    entities = [PokemonEntity::class,
        ProgressEntity::class,
        TypeEntity::class,
        PokemonTypeEntity::class],
    version = 1,
    exportSchema = false)
abstract class PokemonDatabase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun progressDao(): ProgressDao
    abstract fun typeDao(): TypeDao
    abstract fun pokemonTypeDao(): PokemonTypeDao

    companion object {
        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        fun getDatabase(context: Context): PokemonDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDatabase::class.java,
                    "pokemon_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}