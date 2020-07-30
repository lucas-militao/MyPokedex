package com.example.mypokedex.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mypokedex.model.pokemon.dto.PokemonDto
import com.example.mypokedex.network.PokemonApiService
import com.example.mypokedex.network.retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PokemonViewModel(
    application: Application
): AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope( viewModelJob + Dispatchers.Main )

    private val _pokemonsList = MutableLiveData<ArrayList<PokemonDto>>()
    val pokemonsList: LiveData<ArrayList<PokemonDto>>
        get() = _pokemonsList

    private val _next = MutableLiveData<String>()
    val next: LiveData<String>
        get() = _next

    private val _requestNewPage = MutableLiveData<Boolean>()
    val requestNewPage: LiveData<Boolean>
        get() = _requestNewPage

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    private val _searchViewOpen = MutableLiveData<Boolean>()
    val searchViewOpen: LiveData<Boolean>
        get() = _searchViewOpen

    private val retrofitService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }

    init {
        requestPokemonList()
        _searchViewOpen.value = false
    }

    fun requestPokemonList() {
        coroutineScope.launch {
            showProgress()
            val requestResult = (
                if (_next.value == null) {
                    _pokemonsList.value = null
                    retrofitService.getList(0, 20)
                } else {
                    retrofitService.getNextList(_next.value!!)
                }
            )

            try {
                val result = requestResult.await()
                val pokemons = ArrayList<PokemonDto>()
                if (_pokemonsList.value != null) pokemons.addAll(_pokemonsList.value!!)

                for (item in result.results!!) {
                    pokemons.add(retrofitService.getPokemonByNameOrId(item.name).await())
                }
                _pokemonsList.value = pokemons
                _next.value = result.next
            } catch (e: Exception) {
                Log.e("REQUEST PAGE ERROR", "requestPokemonList: ${e.message}")
            }
            closeProgress()
        }
    }

    fun searchPokemon(name: String) {
        coroutineScope.launch {
            showProgress()
            val getDeferred = retrofitService.getPokemonByNameOrId(name)
            try {
                val pokemon = getDeferred.await()
                _pokemonsList.value = arrayListOf(pokemon)
            } catch (e: Exception) {
                Log.e("REQUEST PAGE ERROR", "requestNextPage: ${e.message}" )
            }
            closeProgress()
        }
    }

    fun requestPage() {
        _requestNewPage.value = true
    }

    fun pageRequested() {
        _requestNewPage.value = false
    }

    fun showProgress() {
        _showProgress.value = true
    }

    fun closeProgress() {
        _showProgress.value = false
    }

    fun searchViewEnabled() {
        _searchViewOpen.value = true
    }

    fun searchViewClosed() {
        _next.value = null
        _searchViewOpen.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}