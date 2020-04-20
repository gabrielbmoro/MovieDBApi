package com.gabrielbmoro.programmingchallenge.repository.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoggedInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("NETWORK", "Request-->${chain.request().body().toString()}")
        val response = chain.proceed(chain.request())
        Log.d("NETWORK", "Response-->$response")
        return response
    }
}