package com.example.mypokedex.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mypokedex.databinding.PokemonInfoFragmentBinding
import com.example.mypokedex.model.pokemon.ui.Pokemon
import com.example.mypokedex.viewmodel.PokemonViewModel

class PokemonInfoFragment: Fragment() {

    lateinit var binding: PokemonInfoFragmentBinding
    lateinit var viewModel: PokemonViewModel
    lateinit var pokemon: Pokemon

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokemonInfoFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)

        pokemon = PokemonInfoFragmentArgs.fromBundle(arguments!!).pokemon

        setupView()

        return binding.root
    }

    private fun setupView() {
        binding.pokemon = pokemon
        setToolbarTitle(pokemon.name)
        binding.executePendingBindings()
    }

    private fun setToolbarTitle(pokemonName: String) {
        with((activity as AppCompatActivity).supportActionBar) {
            this?.setTitle(pokemonName)
        }
    }
}