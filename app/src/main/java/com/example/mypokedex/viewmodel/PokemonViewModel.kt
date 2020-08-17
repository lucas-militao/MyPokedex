package com.example.mypokedex.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mypokedex.model.pokemon.dto.PokemonDto
import com.example.mypokedex.model.pokemon.entity.PokemonEntity
import com.example.mypokedex.model.type.dto.TypeDto
import com.example.mypokedex.model.type.entity.TypeEntity
import com.example.mypokedex.network.PokemonApiService
import com.example.mypokedex.network.retrofit
import com.example.mypokedex.repository.pokemon.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class PokemonViewModel(
    application: Application
): AndroidViewModel(application) {

    private val repository = PokemonRepository(application.applicationContext)

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope( viewModelJob + Dispatchers.Main )

    private val _pokemons = MutableLiveData<List<PokemonEntity>>()
    val pokemons: LiveData<List<PokemonEntity>> = _pokemons

    private val _pokemonInfo = MutableLiveData<PokemonDto>()
    val pokemonInfo: LiveData<PokemonDto>
        get() = _pokemonInfo

    private val _pokemonsList = MutableLiveData<ArrayList<PokemonDto>>()
    val pokemonsList: LiveData<ArrayList<PokemonDto>>
        get() = _pokemonsList

    private val _pokemonTypes = MutableLiveData<List<TypeEntity>>()
    val pokemonTypes: LiveData<List<TypeEntity>>
        get() = _pokemonTypes

    private val _next = MutableLiveData<String>()
    val next: LiveData<String>
        get() = _next

    private val _searchViewOpen = MutableLiveData<Boolean>()
    val searchViewOpen: LiveData<Boolean>
        get() = _searchViewOpen

    private val _filterOn = MutableLiveData<Boolean>()
    val filterOn: LiveData<Boolean>
        get() = _filterOn

    private val retrofitService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }

    init {
        _searchViewOpen.value = false
        _filterOn.value = false
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

    fun filtering() {
        _filterOn.value = true
    }

    fun getPokemonInfo(pokemonDto: PokemonDto) {
        _pokemonInfo.value = pokemonDto
    }

    fun pokemonInfoDelivered() {
        _pokemonInfo.value = null
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