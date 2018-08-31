package com.gabrielbmoro.programmingchallenge

import android.app.Application
import android.arch.persistence.room.Room
import com.gabrielbmoro.programmingchallenge.dao.DataBaseFactory

/**
 * The base context.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class ProgrammingChallengeApp : Application() {

    /*        StaticCollections.mappDataBuilder = Room.databaseBuilder(this,
                DataBaseFactory::class.java,
                "diin-database")
                .allowMainThreadQueries()
                .build()
*/

    companion object {
        var mappDataBuilder : DataBaseFactory? = null
    }

    override fun onCreate() {
        super.onCreate()

        mappDataBuilder = Room.databaseBuilder(this,
                DataBaseFactory::class.java,
                "programmingchallengedb")
                .allowMainThreadQueries()
                .build()

    }

}