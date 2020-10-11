package com.gabrielbmoro.programmingchallenge.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.gabrielbmoro.programmingchallenge.R
import kotlinx.android.synthetic.main.dialog_theme_selection.*

class ThemeSelectionDialog(val currentTheme: Int) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_theme_selection, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferencesOptions = resources.getTextArray(R.array.pref_themes)
        if (preferencesOptions.size >= 3) {
            val optionValue = when (currentTheme) {
                AppCompatDelegate.MODE_NIGHT_AUTO -> preferencesOptions[0]
                AppCompatDelegate.MODE_NIGHT_YES -> preferencesOptions[1]
                AppCompatDelegate.MODE_NIGHT_NO -> preferencesOptions[2]
                else -> ""
            }
            dialog_theme_selection_group.setCheckTo(optionValue)

            dialog_theme_selection_group.setupCallback { selectedItem ->
                targetFragment?.onActivityResult(
                        REQUEST_CODE,
                        when (selectedItem) {
                            preferencesOptions[0] -> AppCompatDelegate.MODE_NIGHT_AUTO
                            preferencesOptions[1] -> AppCompatDelegate.MODE_NIGHT_YES
                            preferencesOptions[2] -> AppCompatDelegate.MODE_NIGHT_NO
                            else -> AppCompatDelegate.MODE_NIGHT_NO
                        },
                        null
                )
                dismiss()
            }
        }
    }

    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, ThemeSelectionDialog::javaClass.name)
    }

    fun setTargetFragment(fragment: Fragment) {
        super.setTargetFragment(fragment, REQUEST_CODE)
    }

    companion object {
        const val REQUEST_CODE = 1231
    }
}