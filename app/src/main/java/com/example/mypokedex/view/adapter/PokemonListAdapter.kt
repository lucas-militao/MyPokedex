package com.example.mypokedex.view.adapter

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mypokedex.R
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
            binding.pokemonName.text = pokemon.name
            binding.typeContainer.removeAllViews()
            binding.itemPokemonContainer.setOnClickListener { onClick(pokemon) }

            for (type in pokemon.types) {
                val newType = TextView(binding.typeContainer.context)

                with(newType) {
                    this.text = type.type.name
                    this.setPadding(8,8,8,8)

                    val colorResource = when(type.type.name) {
                        "fire" -> resources.getColor(R.color.type_fire)
                        "grass" -> resources.getColor(R.color.type_grass)
                        "ground" -> resources.getColor(R.color.type_ground)
                        "bug" -> resources.getColor(R.color.type_bug)
                        "water" -> resources.getColor(R.color.type_water)
                        "flying" -> resources.getColor(R.color.type_flying)
                        "ghost" -> resources.getColor(R.color.type_ghost)
                        "poison" -> resources.getColor(R.color.type_poison)
                        "fairy" -> resources.getColor(R.color.type_fairy)
                        else -> resources.getColor(R.color.type_normal)
                    }

                    val gd = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(colorResource, colorResource))
                    gd.cornerRadius = 10f
                    this.background = gd

                    val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT)
                    lp.marginStart = 2
                    lp.marginEnd = 2

                    this.layoutParams = lp

                    binding.typeContainer.addView(this)
                }
            }

            val requestOptions = RequestOptions()
                .error(R.drawable.pokeball_error)

            Glide.with(binding.root)
                .load(pokemon.sprites.frontDefault)
                .apply(requestOptions)
                .into(binding.pokemonIcon)
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
