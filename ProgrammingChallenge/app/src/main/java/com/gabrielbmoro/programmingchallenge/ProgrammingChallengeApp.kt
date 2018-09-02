package com.gabrielbmoro.programmingchallenge

import android.app.Application
import android.arch.persistence.room.Room
import com.gabrielbmoro.programmingchallenge.dao.DataBaseFactory
import com.gabrielbmoro.programmingchallenge.network_monitor.CheckInternet

/**
 * The base context.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class ProgrammingChallengeApp : Application() {

    companion object {
        var mappDataBuilder : DataBaseFactory? = null
        var mbHasNetworkConnection : Boolean = false
    }

    /**
     * This is the first method called when
     * the app starts.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onCreate() {
        super.onCreate()

        mappDataBuilder = Room.databaseBuilder(this,
                DataBaseFactory::class.java,
                "programmingchallengedb")
                .allowMainThreadQueries()
                .build()


        mbHasNetworkConnection = CheckInternet.checkInternet(this)
    }

}