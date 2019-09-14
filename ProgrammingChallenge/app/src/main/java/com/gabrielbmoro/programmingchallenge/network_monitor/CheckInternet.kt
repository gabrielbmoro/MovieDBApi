package com.gabrielbmoro.programmingchallenge.network_monitor

import android.content.Context
import android.net.ConnectivityManager

/**
 * This class is used to check the internet state.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
object CheckInternet {

    /**
     * Check the internet state.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    fun checkInternet(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        return if (activeNetwork != null) {
            (activeNetwork.type == ConnectivityManager.TYPE_WIFI || activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
        } else {
            false
        }
    }
}