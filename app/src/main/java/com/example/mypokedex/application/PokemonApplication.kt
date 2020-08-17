package com.example.mypokedex.application

import android.app.Application
import android.content.Context
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mypokedex.work.SavePokemonTypesWorker
import com.example.mypokedex.work.SavePokemonsWorker
import com.facebook.stetho.Stetho
import java.util.concurrent.TimeUnit

class PokemonApplication: Application(){

    companion object {
        private var instance: PokemonApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    init {
        instance = this
    }


    override fun onCreate() {
        super.onCreate()

        stetho()
        savePokemonTypesWork()
        savePokemonsWork()
    }

    private fun stetho() {
        Stetho.initializeWithDefaults(this)
    }

    fun savePokemonsWork() {
        val savePokemons =
            PeriodicWorkRequestBuilder<SavePokemonsWorker>(6, TimeUnit.HOURS)
                .addTag("Save pokemons")
                .build()

        WorkManager.getInstance(applicationContext).enqueue(savePokemons)
    }

    fun savePokemonTypesWork() {
        val saveTypes =
            PeriodicWorkRequestBuilder<SavePokemonTypesWorker>(12, TimeUnit.HOURS)
                .addTag("Save types")
                .build()

        WorkManager.getInstance(applicationContext).enqueue(saveTypes)
    }
}

