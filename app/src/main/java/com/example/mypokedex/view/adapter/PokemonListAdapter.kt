package com.example.mypokedex.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.databinding.ItemPokemonBinding
import com.example.mypokedex.model.pokemon.entity.PokemonEntity
import com.example.mypokedex.model.pokemontype.PokemonWithTypes

class PokemonListAdapter(var onClick: (PokemonWithTypes) -> Unit): ListAdapter<PokemonWithTypes, PokemonListAdapter.PokemonViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<PokemonWithTypes>() {
        override fun areItemsTheSame(
            oldItem: PokemonWithTypes,
            newItem: PokemonWithTypes
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PokemonWithTypes,
            newItem: PokemonWithTypes
        ): Boolean {
            return oldItem.pokemon.id == newItem.pokemon.id
        }

    }

    inner class PokemonViewHolder(private var binding: ItemPokemonBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonWithTypes, onClick: (PokemonWithTypes) -> Unit) {
            binding.pokemon = pokemon
            binding.itemPokemonContainer.setOnClickListener { onClick(pokemon) }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon, onClick)
    }
}
