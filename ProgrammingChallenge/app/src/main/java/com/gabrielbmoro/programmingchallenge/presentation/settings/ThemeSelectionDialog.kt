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

class ThemeSelectionDialog(val currentTheme : Int) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_theme_selection, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // select the current theme
        when(currentTheme){
            AppCompatDelegate.MODE_NIGHT_NO -> dialog_theme_selection_day_option.isChecked = true
            AppCompatDelegate.MODE_NIGHT_YES -> dialog_theme_selection_night_option.isChecked = true
            AppCompatDelegate.MODE_NIGHT_AUTO -> dialog_theme_selection_automatic_option.isChecked = true
        }

        dialog_theme_selection_group.setOnCheckedChangeListener { _, checkedId ->
            targetFragment?.onActivityResult(
                    REQUEST_CODE,
                    when (checkedId) {
                        R.id.dialog_theme_selection_day_option -> AppCompatDelegate.MODE_NIGHT_NO
                        R.id.dialog_theme_selection_night_option -> AppCompatDelegate.MODE_NIGHT_YES
                        R.id.dialog_theme_selection_automatic_option -> AppCompatDelegate.MODE_NIGHT_AUTO
                        else -> AppCompatDelegate.MODE_NIGHT_NO
                    },
                    null
            )
            dismiss()
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