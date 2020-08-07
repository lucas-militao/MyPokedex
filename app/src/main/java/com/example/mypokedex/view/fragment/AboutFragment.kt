package com.example.mypokedex.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mypokedex.databinding.AboutFragmentBinding

class AboutFragment: Fragment() {

    private lateinit var binding: AboutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AboutFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        setupView()
        subscribeUi()

        return binding.root
    }

    private fun setupView() {
        //TODO: Ver como adicionar função para voltar a página anterior
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun subscribeUi() {
    }

}