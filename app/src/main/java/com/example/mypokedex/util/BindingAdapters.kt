package com.example.mypokedex.util

import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mypokedex.R
import com.example.mypokedex.model.pokemon.entity.PokemonEntity
import com.example.mypokedex.model.pokemontype.PokemonWithTypes
import com.example.mypokedex.model.type.entity.TypeEntity
import com.example.mypokedex.model.type.ui.Type
import com.example.mypokedex.view.adapter.PokemonListAdapter
import java.util.*

@BindingAdapter("pokemonType")
fun pokemonType(container: LinearLayout, types: List<Type>) {

    for (type in types) {

        val textView = TextView(container.context)

        with(textView) {
            this.text = type.name.toUpperCase(Locale.ROOT)
            this.setTypeface(null, Typeface.BOLD)
            this.setPadding(8,8,8,8)

            val colorResource = when(type.name) {
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

            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.marginStart = 2
            lp.marginEnd = 2

            this.layoutParams = lp
            this.isSingleLine = true
        }

        container.addView(textView)
    }
}

@BindingAdapter("pokemonTypeEntity")
fun pokemonTypeEntity(container: LinearLayout, types: List<TypeEntity>) {

    for (type in types) {

        val textView = TextView(container.context)

        with(textView) {
            this.text = type.name.toUpperCase(Locale.ROOT)
            this.setTypeface(null, Typeface.BOLD)
            this.setPadding(8,8,8,8)

            val colorResource = when(type.name) {
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

            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.marginStart = 2
            lp.marginEnd = 2

            this.layoutParams = lp
            this.isSingleLine = true
        }

        container.addView(textView)
    }
}

@BindingAdapter("pokemonName")
fun pokemonName(textView: TextView, name: String) {
    textView.text = name
}

@BindingAdapter("pokemonImage")
fun pokemonImage(imageView: ImageView, url: String) {

    val requestOptions = RequestOptions()
        .error(R.drawable.pokeball_error)

    Glide.with(imageView.context)
        .load(url)
        .apply(requestOptions)
        .into(imageView)
}

@BindingAdapter("pokemonHeight")
fun pokemonHeight(textView: TextView, height: Int) {
    textView.text = textView.context.resources.getString(R.string.pokemon_height, height * 10)
}

@BindingAdapter("pokemonWeight")
fun pokemonWeight(textView: TextView, weight: Int) {
    textView.text = textView.context.resources.getString(R.string.pokemon_weight, weight.toFloat() * 0.1)
}

@BindingAdapter("pokemonList")
fun pokemonList(recyclerView: RecyclerView, data: List<PokemonWithTypes>?) {
    val adapter = recyclerView.adapter as PokemonListAdapter
    adapter.submitList(data)
}
