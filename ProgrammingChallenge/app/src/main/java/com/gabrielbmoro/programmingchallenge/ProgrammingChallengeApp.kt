package com.gabrielbmoro.programmingchallenge

import android.app.Application
import com.gabrielbmoro.programmingchallenge.koin.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * The base context.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class ProgrammingChallengeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            // Android context
            androidContext(this@ProgrammingChallengeApp)
            // modules
            modules(repositoryModule)
        }
    }

}