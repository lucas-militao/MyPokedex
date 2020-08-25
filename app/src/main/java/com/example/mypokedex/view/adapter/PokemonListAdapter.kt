package com.example.mypokedex.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.databinding.ItemPokemonBinding
import com.example.mypokedex.model.pokemon.ui.Pokemon

class PokemonListAdapter(var onClick: (Pokemon) -> Unit): ListAdapter<Pokemon, PokemonListAdapter.PokemonViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(
            oldItem: Pokemon,
            newItem: Pokemon
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Pokemon,
            newItem: Pokemon
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    inner class PokemonViewHolder(private var binding: ItemPokemonBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon, onClick: (Pokemon) -> Unit) {
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
