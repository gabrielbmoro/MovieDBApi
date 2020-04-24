package com.gabrielbmoro.programmingchallenge

import android.content.Context
import com.gabrielbmoro.programmingchallenge.core.repositoryModule
import com.gabrielbmoro.programmingchallenge.core.usecaseModule
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.mockito.Mockito

open class KoinUnitTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        androidContext(Mockito.mock(Context::class.java))
        modules(repositoryModule, usecaseModule)
    }
}