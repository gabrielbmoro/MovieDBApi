package com.gabrielbmoro.programmingchallenge.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imagePath")
fun setImagePath(imageView : ImageView, path : String) {
    Picasso.Builder(imageView.context).build().apply {
        load(path).into(imageView)
    }
}

@BindingAdapter("srcCompat")
fun setImage(imageView: ImageView, drawable : Int) {
    imageView.setImageResource(drawable)
}