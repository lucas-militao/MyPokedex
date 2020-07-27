package com.example.mypokedex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mypokedex.adapter.PokemonListAdapter
import com.example.mypokedex.databinding.PokemonSearchFragmentBinding
import com.example.mypokedex.viewmodel.PokemonViewModel

class PokemonSearchFragment: Fragment() {

    private lateinit var binding: PokemonSearchFragmentBinding
    private val viewModel: PokemonViewModel by lazy {
        ViewModelProviders.of(this).get(PokemonViewModel::class.java)
    }
    private lateinit var adapter: PokemonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokemonSearchFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        setupView()
        subscribeUi()

        return binding.root
    }

    private fun setupView() {
        binding.viewModel = viewModel
        adapter = PokemonListAdapter()
        binding.pokemonList.adapter = adapter
    }

    private fun subscribeUi() {
        viewModel.pokemonsList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}