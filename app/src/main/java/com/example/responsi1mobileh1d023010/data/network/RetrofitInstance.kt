// com/example/responsi1mobileh1d023010/data/remote/RetrofitInstance.kt
package com.example.responsi1mobileh1d023010.data.network

import com.example.responsi1mobileh1d023010.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: FootballDataApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FootballDataApi::class.java)
    }
}
