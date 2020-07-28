package com.example.mypokedex.application

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho

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
    }

    private fun stetho() {
        Stetho.initializeWithDefaults(this)
    }

    fun savePokemonsWork() {
        //TODO: Implementar método para salvar pokemons em background
    }
}

