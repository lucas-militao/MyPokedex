package com.example.mypokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mypokedex.model.ListObjectResponse
import com.example.mypokedex.model.ListResponse
import com.example.mypokedex.network.PokemonApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel: ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        listPokemons()
    }

    private fun listPokemons() {
        PokemonApi.retrofitService.getList(10, 10).enqueue(
            object: Callback<ListResponse<ListObjectResponse>> {
                override fun onFailure(call: Call<ListResponse<ListObjectResponse>>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }

                override fun onResponse(
                    call: Call<ListResponse<ListObjectResponse>>,
                    response: Response<ListResponse<ListObjectResponse>>
                ) {
                    _response.value = "There are available: ${response.body()?.count} pokemons"
                }


            }
        )
    }
}