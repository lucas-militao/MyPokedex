package com.example.mypokedex.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mypokedex.R
import com.example.mypokedex.databinding.PokemonSearchFragmentBinding
import com.example.mypokedex.network.PokemonApiService
import com.example.mypokedex.viewmodel.PokemonViewModel

class PokemonSearchFragment: Fragment() {

    private lateinit var binding: PokemonSearchFragmentBinding
    private val viewModel: PokemonViewModel by lazy {
        ViewModelProviders.of(this).get(PokemonViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokemonSearchFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        setupView()

        return binding.root
    }

    private fun setupView() {
        binding.viewModel = viewModel
    }
}