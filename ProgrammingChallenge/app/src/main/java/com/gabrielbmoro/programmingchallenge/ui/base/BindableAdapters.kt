package com.gabrielbmoro.programmingchallenge.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imagePath")
fun setImagePath(imageView : ImageView, path : String) {
    Glide.with(imageView.context).apply {
        load(path).into(imageView)
    }
}

@BindingAdapter("srcCompat")
fun setImage(imageView: ImageView, drawable : Int) {
    imageView.setImageResource(drawable)
}