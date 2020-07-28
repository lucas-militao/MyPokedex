package com.example.mypokedex.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mypokedex.model.pokemon.dto.PokemonDto
import com.example.mypokedex.repository.pokemon.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PokemonViewModel(
    application: Application
): AndroidViewModel(application) {

    private val repository =
        PokemonRepository(application.applicationContext)

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope( viewModelJob + Dispatchers.Main )

    private val _pokemonsList = MutableLiveData<ArrayList<PokemonDto>>()
    val pokemonsList: LiveData<ArrayList<PokemonDto>>
        get() = _pokemonsList

    private val _next = MutableLiveData<String>()
    val next: LiveData<String>
        get() = _next

    init {
        getFirstList()
    }

    fun getFirstList(offset: Int = 0, limit: Int = 20) {
        coroutineScope.launch {
            _pokemonsList.value = repository.getAll(offset, limit) as ArrayList<PokemonDto>
        }
    }

    fun getNewList() {
        coroutineScope.launch {
            _pokemonsList.value?.addAll(repository.getNext(_next.value!!) as ArrayList<PokemonDto>)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}