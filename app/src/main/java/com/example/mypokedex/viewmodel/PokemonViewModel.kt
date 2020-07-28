package com.example.mypokedex.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mypokedex.model.dto.PokemonDto
import com.example.mypokedex.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PokemonViewModel(
    application: Application
): AndroidViewModel(application) {

    private val repository = PokemonRepository(application.applicationContext)

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope( viewModelJob + Dispatchers.Main )

    private val _pokemonsList = MutableLiveData<List<PokemonDto>>()
    val pokemonsList: LiveData<List<PokemonDto>>
        get() = _pokemonsList

    init {
        getAll()
    }

    fun getAll() {
        coroutineScope.launch {
            val list = repository.getAll()
            Log.i("REQUEST RESPONSE", "resposta: $list")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}