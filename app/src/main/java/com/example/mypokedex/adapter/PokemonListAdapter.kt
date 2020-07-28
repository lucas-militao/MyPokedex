package com.example.mypokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mypokedex.databinding.ItemPokemonBinding
import com.example.mypokedex.model.pokemon.dto.PokemonDto

class PokemonListAdapter: ListAdapter<PokemonDto, PokemonListAdapter.PokemonViewHolder>(DiffCallback) {

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
        : RecyclerView.ViewHolder(binding.root){
        fun bind(pokemon: PokemonDto) {
            binding.pokemonName.text = pokemon.name
            Glide.with(binding.root)
                .load(pokemon.sprites.frontDefault)
                .into(binding.pokemonIcon)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        var pokemon = getItem(position)
        holder.bind(pokemon)
    }
}
