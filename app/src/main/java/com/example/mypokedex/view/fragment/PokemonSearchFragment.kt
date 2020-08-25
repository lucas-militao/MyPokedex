package com.example.mypokedex.view.fragment

import android.os.Bundle
import android.transition.TransitionManager
import android.view.*
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mypokedex.R
import com.example.mypokedex.databinding.PokemonSearchFragmentBinding
import com.example.mypokedex.model.pokemon.ui.Pokemon
import com.example.mypokedex.model.type.entity.TypeEntity
import com.example.mypokedex.util.toPokemon
import com.example.mypokedex.util.toType
import com.example.mypokedex.view.adapter.PokemonListAdapter
import com.example.mypokedex.viewmodel.PokemonViewModel
import com.google.android.material.chip.Chip

class PokemonSearchFragment: Fragment() {

    private lateinit var binding: PokemonSearchFragmentBinding
    private var searchViewOpen = false
    private var filterOn = false
    private lateinit var viewModel: PokemonViewModel

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
        setupToolbar()
        binding.viewModel = viewModel
        binding.pokemonList.adapter = PokemonListAdapter(onClick = {
            viewModel.getPokemonInfo(it.id)
        })

        setHasOptionsMenu(true)
    }

    private fun subscribeUi() {

        viewModel.pokemonTypes.observe(viewLifecycleOwner, object: Observer<List<TypeEntity>> {
            override fun onChanged(data: List<TypeEntity>?) {
                data ?: return
                val chipGroup = binding.pokemonTypeFilter
                val inflator = LayoutInflater.from(chipGroup.context)
                val children = data.map { type ->
                    val chip = inflator.inflate(R.layout.type, chipGroup, false) as Chip
                    chip.text = type.name
                    chip.tag = type.name
                    
                    chip.setOnCheckedChangeListener { button, isChecked ->
                        if (isChecked) {
                            viewModel.searchPokemonsByType(button.tag.toString())
                            viewModel.turnOnFilter()
                        }
                    }
                    
                    chip
                }

                chipGroup.setOnCheckedChangeListener { group, checkedId ->
                    if (group.checkedChipId == View.NO_ID) {
                        viewModel.turnOffFilter()
                    }

                }

                chipGroup.removeAllViews()

                for (chip in children) {
                    chipGroup.addView(chip)
                }
            }
        })

        viewModel.pokemonTypes().observe(viewLifecycleOwner, Observer {
            viewModel.updateTypes(it)
        })

        viewModel.filterOn.observe(viewLifecycleOwner, Observer {
            filterOn = it
        })

        viewModel.pokemonsList().observe(viewLifecycleOwner, Observer {
            if (!searchViewOpen && !filterOn) {
                val pokemons = arrayListOf<Pokemon>()
                for (result in it) {
                    pokemons.add(result.pokemon.toPokemon())
                    for (type in result.type) {
                        pokemons[pokemons.size - 1].types.add(type.toType())
                    }
                }
                viewModel.updateList(pokemons)
            }
        })

        viewModel.searchViewOpen.observe(viewLifecycleOwner, Observer {
            searchViewOpen = it
        })

        viewModel.pokemons.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                binding.pokemonList.visibility = View.VISIBLE
                viewModel.cancelProgress()
            } else {
                viewModel.showProgress()
            }
        })

        viewModel.pokemonInfo.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(PokemonSearchFragmentDirections.actionPokemonSearchFragmentToPokemonInfoFragment(it))
                viewModel.pokemonInfoDelivered()
            }
        })

        viewModel.progressOn.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        val menuItem = menu.findItem(R.id.searchView)
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object: OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (!p0.isNullOrEmpty()) {
                    viewModel.searchPokemonBuName(p0)
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

        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        with((activity as AppCompatActivity).supportActionBar) {
            this?.setTitle("My Pokedex")
        }
    }
}