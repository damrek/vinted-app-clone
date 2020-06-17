package com.dmareca.vintedmvvm.view.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dmareca.vintedmvvm.network.ApiAdapter
import com.squareup.picasso.Picasso

class ImageBinder {
    companion object {
        @BindingAdapter("imagenUrl")
        @JvmStatic
        fun setImageUrl(imageView: ImageView, imageUrl: String?) {
            Picasso.get()
                .load("http://"+ApiAdapter.LOCALHOST_IP+":59273/productos/images/" +imageUrl + ".jpg")
                .placeholder(com.dmareca.vintedmvvm.R.drawable.logo)
                .into(imageView)
        }
    }
}