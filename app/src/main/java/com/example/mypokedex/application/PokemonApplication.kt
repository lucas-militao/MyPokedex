package com.example.mypokedex.application

import android.app.Application
import com.facebook.stetho.Stetho

class PokemonApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        stetho()
    }

    fun stetho() {
        Stetho.initializeWithDefaults(this)
    }

    fun savePokemonsWork() {
        //TODO: Implementar método para salvar pokemons em background
    }
}

