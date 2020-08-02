package com.gabrielbmoro.programmingchallenge.presentation.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gabrielbmoro.programmingchallenge.R

class SettingsActivity : AppCompatActivity(R.layout.activity_settings) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(
                R.id.activity_settings_preferences_layout,
                SettingsPreferenceFragment()
        ).commit()

        supportActionBar?.title = getString(R.string.settings)
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, SettingsActivity::class.java))
        }
    }
}