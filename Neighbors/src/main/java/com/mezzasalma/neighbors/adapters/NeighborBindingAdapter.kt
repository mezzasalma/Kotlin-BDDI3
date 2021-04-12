package com.mezzasalma.neighbors.adapters

import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mezzasalma.neighbors.R

object NeighborBindingAdapter {
    @BindingAdapter("app:avatar") // Crée une balise disponible dans le layout directement
    @JvmStatic
    fun bindImage(imageView: ImageView, url: String) { // Display neighbor Avatar
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_baseline_person_outline_24)
            .error(R.drawable.ic_baseline_person_outline_24)
            .skipMemoryCache(false)
            .into(imageView)
    }

    @BindingAdapter("app:favorite")
    @JvmStatic
    fun bindFavorite(imageButton: ImageButton, boolean: Boolean) {
        if (boolean) {
            imageButton.setImageResource(R.drawable.ic_baseline_star_24)
        } else {
            imageButton.setImageResource(R.drawable.ic_baseline_star_outline_24)
        }
    }
}
