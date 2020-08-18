package com.example.mypokedex.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.model.pokemon.entity.PokemonEntity
import com.example.mypokedex.model.pokemontype.PokemonWithTypes
import com.example.mypokedex.model.type.entity.TypeEntity
import com.example.mypokedex.repository.pokemon.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PokemonViewModel(
    application: Application
): AndroidViewModel(application) {

    private val repository = PokemonRepository(application.applicationContext)

    private val viewModelJob = Job()

    private val _pokemons = MutableLiveData<List<PokemonEntity>>()
    val pokemons: LiveData<List<PokemonEntity>> = _pokemons

    private val _pokemonId = MutableLiveData<Int>()
    val pokemonId: LiveData<Int>
        get() = _pokemonId

    private val _pokemonInfo = MutableLiveData<PokemonWithTypes>()
    val pokemonInfo: LiveData<PokemonWithTypes>
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

    fun pokemonsList(): LiveData<List<PokemonEntity>> {
        return repository.getPokemonsLiveData()
    }

    fun searchViewEnabled() {
        _searchViewOpen.value = true
    }

    fun searchViewClosed() {
        _next.value = null
        _searchViewOpen.value = false
    }

    fun getPokemonId(id: Int) {
        _pokemonId.value = id
    }

    fun getPokemonInfo(id: Int) {
        viewModelScope.launch {
            val p = repository.getPokemon(id)
            _pokemonInfo.value = p
        }
    }

    fun pokemonIdDelivered() {
        _pokemonId.value = null
    }

    fun updateList(list: List<PokemonEntity>) {
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