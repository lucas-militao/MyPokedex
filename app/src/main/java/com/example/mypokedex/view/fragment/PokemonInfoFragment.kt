package com.example.mypokedex.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mypokedex.R
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

        Glide.with(this)
            .load(pokemonDto.sprites.frontDefault)
            .into(binding.pokemonImage)

        binding.pokemonHeight.text = pokemonDto.height.toString()
        binding.pokemonWeight.text = pokemonDto.weight.toString()

        for (type in pokemonDto.types) {
            if (binding.pokemonType.text.isNullOrEmpty())
                binding.pokemonType.text = type.type.name
            else {
                with(binding.pokemonType) {
                    this.text = resources.getString(R.string.pokemon_types, this.text, type.type.name)
                }
            }
        }
    }

    private fun subscribeUi() {

    }

    private fun setToolbarTitle(pokemonName: String) {
        with((activity as AppCompatActivity).supportActionBar) {
            this?.setTitle(pokemonName)
        }
    }
}