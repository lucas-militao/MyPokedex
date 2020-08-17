package com.example.mypokedex.view.fragment

import android.os.Bundle
import android.transition.TransitionManager
import android.view.*
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mypokedex.R
import com.example.mypokedex.databinding.PokemonSearchFragmentBinding
import com.example.mypokedex.model.type.dto.TypeDto
import com.example.mypokedex.model.type.entity.TypeEntity
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
                    }
                    
                    chip
                }

                chipGroup.setOnCheckedChangeListener { group, checkedId ->
                    if (group.checkedChipId == View.NO_ID) {
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
}