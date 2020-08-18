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
import com.example.mypokedex.viewmodel.PokemonViewModel

class PokemonInfoFragment: Fragment() {

    lateinit var binding: PokemonInfoFragmentBinding
    lateinit var viewModel: PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokemonInfoFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)

        setupView(PokemonInfoFragmentArgs.fromBundle(arguments!!).pokemonId)
        subscribeUi()
        return binding.root
    }

    private fun setupView(id: Int) {
        viewModel.getPokemonInfo(id)
    }

    private fun subscribeUi() {
        viewModel.pokemonInfo.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                binding.pokemon = it
                binding.executePendingBindings()
                setToolbarTitle(it.pokemon.name)
            }
        })
    }

    private fun setToolbarTitle(pokemonName: String) {
        with((activity as AppCompatActivity).supportActionBar) {
            this?.setTitle(pokemonName)
        }
    }
}