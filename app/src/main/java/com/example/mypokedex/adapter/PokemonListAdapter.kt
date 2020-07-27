package com.example.mypokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.databinding.ItemPokemonBinding
import com.example.mypokedex.model.ListObjectResponse

class PokemonListAdapter: ListAdapter<ListObjectResponse, PokemonListAdapter.PokemonViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<ListObjectResponse>() {
        override fun areItemsTheSame(
            oldItem: ListObjectResponse,
            newItem: ListObjectResponse
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: ListObjectResponse,
            newItem: ListObjectResponse
        ): Boolean {
            return oldItem.name == newItem.name
        }

    }

    inner class PokemonViewHolder(private var binding: ItemPokemonBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(pokemon: ListObjectResponse) {
            binding.pokemonName.text = pokemon.name
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
