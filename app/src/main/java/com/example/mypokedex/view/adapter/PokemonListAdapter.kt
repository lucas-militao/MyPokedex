package com.example.mypokedex.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.databinding.ItemPokemonBinding
import com.example.mypokedex.model.pokemon.dto.PokemonDto

class PokemonListAdapter(var onClick: (PokemonDto) -> Unit): ListAdapter<PokemonDto, PokemonListAdapter.PokemonViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<PokemonDto>() {
        override fun areItemsTheSame(
            oldItem: PokemonDto,
            newItem: PokemonDto
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: PokemonDto,
            newItem: PokemonDto
        ): Boolean {
            return oldItem.name == newItem.name
        }

    }

    inner class PokemonViewHolder(private var binding: ItemPokemonBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonDto, onClick: (PokemonDto) -> Unit) {
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
