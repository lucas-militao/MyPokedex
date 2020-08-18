package com.example.mypokedex.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.databinding.ItemPokemonBinding
import com.example.mypokedex.model.pokemon.entity.PokemonEntity

class PokemonListAdapter(var onClick: (Int) -> Unit): ListAdapter<PokemonEntity, PokemonListAdapter.PokemonViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<PokemonEntity>() {
        override fun areItemsTheSame(
            oldItem: PokemonEntity,
            newItem: PokemonEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PokemonEntity,
            newItem: PokemonEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    inner class PokemonViewHolder(private var binding: ItemPokemonBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonEntity, onClick: (Int) -> Unit) {
            binding.pokemon = pokemon
            binding.itemPokemonContainer.setOnClickListener { onClick(pokemon.id) }
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
