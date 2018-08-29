package com.gabrielbmoro.programmingchallenge.ui

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gabrielbmoro.programmingchallenge.Constants
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.api.ApiServiceInterface
import com.gabrielbmoro.programmingchallenge.models.Page
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.OkHttpClient



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //?sort_by=vote_average.desc
        //?sort_by=popularity.desc

        val asyncTask = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                val call = ApiServiceInterface.create().getMovies(Constants.API_KEY, "vote_average.desc")
                call.enqueue(object : Callback<Page> {
                    override fun onFailure(call: Call<Page>, t: Throwable) {
                        Log.d("REsponse", t.toString())
                    }

                    override fun onResponse(call: Call<Page>, response: Response<Page>) {
                        val pgPageReceived = response.body()
                        if(pgPageReceived != null) {
                            Log.d("REsponse", "Sucesso olha ai: ${pgPageReceived.mnTotalResults}")
                        }
                    }
                })
                return null
            }
        }
        asyncTask.execute()

    }
}
