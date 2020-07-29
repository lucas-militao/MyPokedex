package com.example.mypokedex.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.R
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
            binding.pokemonList.visibility = View.VISIBLE
        })

        binding.pokemonList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.doRequest()
                }
            }
        })

        viewModel.requestNewPage.observe(viewLifecycleOwner, Observer {
            if (it == true && viewModel.next.value != null) {
                viewModel.requestNextPage(viewModel.next.value!!)
            }
        })

        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_bar, menu)
    }

    
}