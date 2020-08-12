package com.example.mypokedex.view.fragment

import android.os.Bundle
import android.transition.TransitionManager
import android.view.*
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mypokedex.R
import com.example.mypokedex.databinding.PokemonSearchFragmentBinding
import com.example.mypokedex.model.type.Type
import com.example.mypokedex.view.adapter.PokemonListAdapter
import com.example.mypokedex.viewmodel.PokemonViewModel
import com.google.android.material.chip.Chip

class PokemonSearchFragment: Fragment() {

    private lateinit var binding: PokemonSearchFragmentBinding
    private lateinit var viewModel: PokemonViewModel
    private lateinit var adapter: PokemonListAdapter

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokemonSearchFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)

        setupView()
        subscribeUi()

        return binding.root
    }

    private fun setupView() {
        binding.viewModel = viewModel
        binding.pokemonList.adapter = PokemonListAdapter(onClick = {})
//        setupRecyclerView()
        setHasOptionsMenu(true)
    }

    private fun subscribeUi() {
//        viewModel.pokemonsList.observe(viewLifecycleOwner, Observer {
//            adapter.submitList(it)
//            binding.pokemonList.visibility = View.VISIBLE
//            viewModel.pageRequested()
//        })
//
//        viewModel.requestNewPage.observe(viewLifecycleOwner, Observer {
//            if (it == true && viewModel.searchViewOpen.value == false && viewModel.filterOn.value == false) {
//                viewModel.requestPokemonList()
//            }
//        })
//
//        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
//            binding.progressBar.visibility = if(it) {
//                View.VISIBLE
//            } else {
//                View.GONE
//            }
//        })
//

//        viewModel.pokemons.observe(viewLifecycleOwner, Observer {
//            adapter.submitList(it)
//            binding.pokemonList.visibility = View.VISIBLE
//        })

        viewModel.pokemonTypes.observe(viewLifecycleOwner, object: Observer<ArrayList<Type>> {
            override fun onChanged(data: ArrayList<Type>?) {
                data ?: return
                val chipGroup = binding.pokemonTypeFilter
                val inflator = LayoutInflater.from(chipGroup.context)
                val children = data.map { type ->
                    val chip = inflator.inflate(R.layout.type, chipGroup, false) as Chip
                    chip.text = type.name
                    chip.tag = type.name
                    
                    chip.setOnCheckedChangeListener { button, isChecked ->
                        if (isChecked) {
                            viewModel.applyTypeFilter(button.tag.toString())
                            viewModel.filtering()
                        }
                    }
                    
                    chip
                }

                chipGroup.setOnCheckedChangeListener { group, checkedId ->
                    if (group.checkedChipId == View.NO_ID) {
                        viewModel.filterOff()
                    }

                }

                chipGroup.removeAllViews()

                for (chip in children) {
                    chipGroup.addView(chip)
                }
            }
        })

        viewModel.pokemonsList().observe(viewLifecycleOwner, Observer {
            viewModel.updateList(it)
        })

        viewModel.searchViewOpen.observe(viewLifecycleOwner, Observer {
            //TODO: Buscar forma de desativar filtro quando barra de pesquisa está aberta
        })

        viewModel.pokemonInfo.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(PokemonSearchFragmentDirections
                    .actionPokemonSearchFragmentToPokemonInfoFragment(it))
                viewModel.pokemonInfoDelivered()
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        val menuItem = menu.findItem(R.id.searchView)
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object: OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (!p0.isNullOrEmpty()) {
                    viewModel.searchPokemon(p0)
                    binding.pokemonList.visibility = View.GONE
                }
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

//    private fun setupRecyclerView() {
//
//        adapter = PokemonListAdapter(onClick = {
//            viewModel.getPokemonInfo(it)
//        })
//
//        with(binding.pokemonList) {
//            this.adapter = this@PokemonSearchFragment.adapter
//
//            val nestedScrollPokemons = binding.nestedScrollPokemons
//
//            nestedScrollPokemons.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
//
//                if (scrollY == ( v.getChildAt(0).measuredHeight - v.measuredHeight )) {
//                    viewModel.requestPage()
//                }
//
//            })
//        }
//
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.searchView -> {
                TransitionManager.beginDelayedTransition(activity?.findViewById(R.id.toolbar) as ViewGroup)
                item.expandActionView()
            }
        }

        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}