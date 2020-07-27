package com.example.mypokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mypokedex.model.ListObjectResponse
import com.example.mypokedex.model.ListResponse
import com.example.mypokedex.network.PokemonApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope( viewModelJob + Dispatchers.Main )

    private val _response = MutableLiveData<ListResponse<ListObjectResponse>>()
    val response: LiveData<ListResponse<ListObjectResponse>>
        get() = _response

    init {
        listPokemons()
    }

    private fun listPokemons() {
        coroutineScope.launch {
            var getPropertiesDeferred = PokemonApi.retrofitService.getList(10, 10)

            try {
                _response.value = getPropertiesDeferred.await()
            } catch (e: Exception) {
                "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}