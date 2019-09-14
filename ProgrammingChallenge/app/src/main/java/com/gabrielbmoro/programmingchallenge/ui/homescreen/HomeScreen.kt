package com.gabrielbmoro.programmingchallenge.ui.homescreen

/**
 * This contract provides two interfaces:
 * - Presenter is used to manipulate the app's business
 * objects;
 * - View is used to control the widgets components.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
interface HomeScreenContract {
    /**
     * View defines the setup and update screen operations.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    interface View {
        fun loadPages()
        fun showSnackBar(astrMessage: String)
        fun hideSnackBar()
    }
}