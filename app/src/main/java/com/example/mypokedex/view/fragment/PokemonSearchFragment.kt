package com.example.mypokedex.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mypokedex.R
import com.example.mypokedex.databinding.PokemonSearchFragmentBinding

class PokemonSearchFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pokemon_search_fragment, container, false)

        val binding = PokemonSearchFragmentBinding.inflate(inflater)

        return binding.root
    }
}