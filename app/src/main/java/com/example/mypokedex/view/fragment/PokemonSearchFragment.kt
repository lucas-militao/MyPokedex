package com.example.mypokedex.view.fragment

import android.os.Bundle
import android.transition.TransitionManager
import android.view.*
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.R
import com.example.mypokedex.view.adapter.PokemonListAdapter
import com.example.mypokedex.databinding.PokemonSearchFragmentBinding
import com.example.mypokedex.viewmodel.PokemonViewModel

class PokemonSearchFragment: Fragment() {

    private lateinit var binding: PokemonSearchFragmentBinding
    private val viewModel: PokemonViewModel by lazy {
        ViewModelProviders.of(this).get(PokemonViewModel::class.java)
    }
    private lateinit var adapter: PokemonListAdapter

    override fun onCreateView (
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
        setHasOptionsMenu(true)
    }

    private fun subscribeUi() {
        viewModel.pokemonsList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            binding.pokemonList.visibility = View.VISIBLE
            viewModel.pageRequested()
        })

        binding.pokemonList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.requestPage()
                }
            }
        })

        viewModel.requestNewPage.observe(viewLifecycleOwner, Observer {
            if (it == true && viewModel.searchViewOpen.value == false) {
                viewModel.requestPokemonList()
            }
        })

        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        val menuItem = menu.findItem(R.id.searchView)
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object: OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (!p0.isNullOrEmpty()) viewModel.searchPokemon(p0)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        searchView.setOnSearchClickListener {
            viewModel.searchViewEnabled()
        }

        searchView.setOnCloseListener {
            viewModel.searchViewClosed()
            viewModel.requestPage()
            false
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.searchView -> {
                TransitionManager.beginDelayedTransition(activity?.findViewById(R.id.toolbar) as ViewGroup)
                item.expandActionView()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}