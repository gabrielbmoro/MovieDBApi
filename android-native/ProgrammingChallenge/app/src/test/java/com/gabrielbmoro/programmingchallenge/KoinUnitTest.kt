package com.gabrielbmoro.programmingchallenge

import android.content.Context
import com.gabrielbmoro.programmingchallenge.core.usecaseModule
import com.gabrielbmoro.programmingchallenge.presentation.viewModels.CoroutinesTestRule
import com.gabrielbmoro.programmingchallenge.repository.api.ApiRepository
import com.gabrielbmoro.programmingchallenge.repository.api.ApiRepositoryImpl
import com.gabrielbmoro.programmingchallenge.repository.dataBase.DataBaseRepositoryImpl
import com.gabrielbmoro.programmingchallenge.repository.dataBase.FavoriteMoviesDAO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.mockito.Mockito

open class KoinUnitTest : KoinTest {

    private val fakeRepositoryModule = module{
        single{
            ApiRepositoryImpl(Mockito.mock(ApiRepository::class.java))
        }
        single{
            DataBaseRepositoryImpl(Mockito.mock(FavoriteMoviesDAO::class.java))
        }
    }

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        androidLogger(Level.ERROR)
        androidContext(Mockito.mock(Context::class.java))
        modules(fakeRepositoryModule, usecaseModule)
    }
}