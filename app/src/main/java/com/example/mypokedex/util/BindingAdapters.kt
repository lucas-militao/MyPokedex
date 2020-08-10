package com.example.mypokedex.util

import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mypokedex.R
import com.example.mypokedex.model.type.Type
import com.example.mypokedex.model.type.Types
import java.util.*

@BindingAdapter("pokemonType")
fun pokemonType(container: LinearLayout, types: List<Types>) {

    for (type in types) {

        val textView = TextView(container.context)

        with(textView) {
            this.text = type.type.name.toUpperCase(Locale.ROOT)
            this.setTypeface(null, Typeface.BOLD)
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

            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.marginStart = 2
            lp.marginEnd = 2

            this.layoutParams = lp
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