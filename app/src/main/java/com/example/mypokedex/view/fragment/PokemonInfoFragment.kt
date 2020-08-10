package com.example.mypokedex.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mypokedex.databinding.PokemonInfoFragmentBinding
import com.example.mypokedex.model.pokemon.dto.PokemonDto

class PokemonInfoFragment: Fragment() {

    lateinit var binding: PokemonInfoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokemonInfoFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val pokemon = PokemonInfoFragmentArgs.fromBundle(arguments!!).pokemonInfo

        setupView(pokemon)
        subscribeUi()
        return binding.root
    }

    private fun setupView(pokemonDto: PokemonDto) {
        setToolbarTitle(pokemonDto.name)
        binding.pokemon = pokemonDto
        binding.executePendingBindings()
    }

    private fun subscribeUi() {

    }

    private fun setToolbarTitle(pokemonName: String) {
        with((activity as AppCompatActivity).supportActionBar) {
            this?.setTitle(pokemonName)
        }
    }
}