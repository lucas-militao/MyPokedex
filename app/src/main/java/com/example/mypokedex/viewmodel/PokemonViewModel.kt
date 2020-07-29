package com.example.mypokedex.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mypokedex.model.pokemon.dto.PokemonDto
import com.example.mypokedex.network.PokemonApiService
import com.example.mypokedex.network.retrofit
import com.example.mypokedex.repository.pokemon.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

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

    private val retrofitService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }

    init {
        requestPokemonList(0, 20)
    }

    fun requestPokemonList(offset: Int, limit: Int) {
        coroutineScope.launch {
            showProgress()
            val getDeferred = retrofitService.getList(offset, limit)
            try {
                val result = getDeferred.await()
                val pokemons = ArrayList<PokemonDto>()

                for (item in result.results!!) {
                    pokemons.add(retrofitService.getPokemonByNameOrId(item.name).await())
                }
                _pokemonsList.value = pokemons
                _next.value = result.next
            } catch (e: Exception) {
                Log.e("REQUEST PAGE ERROR", "requestPokemonList: ${e.message}")
            }
            quitProgress()
        }
    }

    fun requestNextPage(url: String) {
        coroutineScope.launch {
            showProgress()
            val getDeferred = retrofitService.getNextList(url)
            try {
                val result = getDeferred.await()
                val pokemons = ArrayList<PokemonDto>()
                pokemons.addAll(_pokemonsList.value!!)

                for (item in result.results!!) {
                    pokemons.add(retrofitService.getPokemonByNameOrId(item.name).await())
                }

                _pokemonsList.value = pokemons
                _next.value = result.next
            } catch (e: Exception) {
                Log.e("REQUEST PAGE ERROR", "requestNextPage: ${e.message}" )
            }
            quitProgress()
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
            quitProgress()
        }
    }

    fun doRequest() {
        _requestNewPage.value = true
    }

    fun pageRequested() {
        _requestNewPage.value = false
    }

    fun showProgress() {
        _showProgress.value = true
    }

    fun quitProgress() {
        _showProgress.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}