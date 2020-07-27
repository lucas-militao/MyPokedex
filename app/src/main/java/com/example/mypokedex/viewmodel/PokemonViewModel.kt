package com.example.mypokedex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mypokedex.model.ListObjectResponse
import com.example.mypokedex.model.ListResponse
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.model.PokemonSprites
import com.example.mypokedex.network.PokemonApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.log

class PokemonViewModel: ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope( viewModelJob + Dispatchers.Main )

    private val _response = MutableLiveData<ListResponse<ListObjectResponse>>()
    val response: LiveData<ListResponse<ListObjectResponse>>
        get() = _response

    private val _pokemonsList = MutableLiveData<List<Pokemon>>()
    val pokemonsList: LiveData<List<Pokemon>>
        get() = _pokemonsList

    init {
        listPokemons()
    }

    private fun listPokemons() {
        coroutineScope.launch {
            var getPropertiesDeferred = PokemonApi.retrofitService.getList(10, 10)
            try {
                _response.value = getPropertiesDeferred.await()
                getPokemons()
            } catch (e: Exception) {
                Log.e("ERRO", "Failure: ${e.message}", e)
            }
        }
    }

    private fun getPokemons() {
        coroutineScope.launch {
            val pokemons = ArrayList<Pokemon>()

            for (result in _response.value?.results!!) {
                val pokemon = PokemonApi.retrofitService.getPokemon(result.name)
                try {
                    pokemons.add(pokemon.await())
                } catch (e: Exception) {
                    Log.e("ERRO", "Failure: ${e.message}", e)
                }
            }

            _pokemonsList.value = pokemons
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}