package com.example.desafio_testedeemprego.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val imgurAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.imgur.com/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build())
            .build()
            .create(ImgurAPI::class.java)
    }
}