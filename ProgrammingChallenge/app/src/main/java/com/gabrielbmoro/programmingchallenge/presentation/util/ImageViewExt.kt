package com.gabrielbmoro.programmingchallenge.presentation.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gabrielbmoro.programmingchallenge.core.ConfigVariables.BASE_IMAGE_ADDRESS

fun ImageView.setImagePath(path: String) {
    Glide.with(context).apply {
        load("${BASE_IMAGE_ADDRESS}$path").into(this@setImagePath)
    }
}