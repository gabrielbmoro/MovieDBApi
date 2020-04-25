package com.gabrielbmoro.programmingchallenge.presentation

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.gabrielbmoro.programmingchallenge.core.ConfigVariables.APP_SECRET_KEY
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

abstract class BaseActivity(@LayoutRes layout : Int) : AppCompatActivity(layout) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCenter.start(application, APP_SECRET_KEY,
                Analytics::class.java, Crashes::class.java)
    }

}