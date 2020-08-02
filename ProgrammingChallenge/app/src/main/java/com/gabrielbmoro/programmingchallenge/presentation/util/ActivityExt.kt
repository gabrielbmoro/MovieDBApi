package com.gabrielbmoro.programmingchallenge.presentation.util

import android.app.Activity
import androidx.appcompat.app.AppCompatDelegate

fun Activity.setDefaultNightModeAccordingToTheValue(value: Int) : Boolean {
    val availableThemes = listOf(AppCompatDelegate.MODE_NIGHT_YES, AppCompatDelegate.MODE_NIGHT_NO, AppCompatDelegate.MODE_NIGHT_AUTO)
    return if (availableThemes.contains(value)) {
        AppCompatDelegate.setDefaultNightMode(value)
        this.recreate()
        true
    } else
        false
}