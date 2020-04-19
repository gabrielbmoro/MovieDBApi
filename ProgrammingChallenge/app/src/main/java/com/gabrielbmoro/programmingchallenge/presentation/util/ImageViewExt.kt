package com.gabrielbmoro.programmingchallenge.presentation.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun ImageView.setImagePath(path: String) {
    Glide.with(context).apply {
        load(path).into(this@setImagePath)
    }
}

fun ImageView.setImage(@DrawableRes drawable: Int) {
    setImageResource(drawable)
}