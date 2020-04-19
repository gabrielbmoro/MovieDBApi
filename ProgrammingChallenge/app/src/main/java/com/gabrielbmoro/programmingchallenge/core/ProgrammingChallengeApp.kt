package com.gabrielbmoro.programmingchallenge.core

import android.app.Application
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
        startKoin {
            // Android context
            androidContext(this@ProgrammingChallengeApp)
            // modules
            modules(listOf(dataReceiverModule, useCaseModule, viewModelModule))
        }
    }

}