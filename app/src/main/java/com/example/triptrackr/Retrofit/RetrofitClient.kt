package com.example.triptrackr.Retrofit

import retrofit2.Retrofit
import retrofit2.Retrofit.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    private var instance : Retrofit? = null
    fun getInstance() : Retrofit {
        if (instance == null)
            instance = Builder()
                .baseUrl("http://10.0.2.2:3000/") // Localhost will be changed to 10.0.2.2 in emulator
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return instance!!
    }
}