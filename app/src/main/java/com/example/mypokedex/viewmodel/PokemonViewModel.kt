package com.example.mypokedex.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.model.pokemon.ui.Pokemon
import com.example.mypokedex.model.pokemontype.PokemonWithTypes
import com.example.mypokedex.model.type.entity.TypeEntity
import com.example.mypokedex.repository.pokemon.PokemonRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class PokemonViewModel(
    application: Application
): AndroidViewModel(application) {

    private val repository = PokemonRepository(application.applicationContext)

    private val viewModelJob = Job()

    private val _pokemons = MutableLiveData<List<PokemonWithTypes>>()
    val pokemons: LiveData<List<PokemonWithTypes>> = _pokemons

    private val _pokemonInfo = MutableLiveData<Pokemon>()
    val pokemonInfo: LiveData<Pokemon>
        get() = _pokemonInfo

    private val _pokemonTypes = MutableLiveData<List<TypeEntity>>()
    val pokemonTypes: LiveData<List<TypeEntity>>
        get() = _pokemonTypes

    private val _next = MutableLiveData<String>()
    val next: LiveData<String>
        get() = _next

    private val _searchViewOpen = MutableLiveData<Boolean>()
    val searchViewOpen: LiveData<Boolean>
        get() = _searchViewOpen


    init {
        _searchViewOpen.value = false
    }

    fun pokemonTypes(): LiveData<List<TypeEntity>> {
        return repository.getTypesLiveData()
    }

    fun pokemonsList(): LiveData<List<PokemonWithTypes>> {
        return repository.getPokemonsLiveData()
    }

    fun searchViewEnabled() {
        _searchViewOpen.value = true
    }

    fun searchViewClosed() {
        _next.value = null
        _searchViewOpen.value = false
    }

    fun getPokemonInfo(id: Int) {
        viewModelScope.launch {
            _pokemonInfo.value = repository.getPokemonById(id)
        }
    }

    fun getPokemon(name: String) {
        viewModelScope.launch {
            try {
                val pokemon = repository.getPokemonByName(name)
                _pokemons.value = arrayListOf(pokemon)
            }catch (e: Exception) {
                Log.e("ERROR IN SEARCHING", "getPokemon: Not found")
            }

        }
    }

    fun pokemonInfoDelivered() {
        _pokemonInfo.value = null
    }

    fun updateList(list: List<PokemonWithTypes>) {
        _pokemons.value = list
    }

    fun updateTypes(list: List<TypeEntity>) {
        _pokemonTypes.value = list
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}