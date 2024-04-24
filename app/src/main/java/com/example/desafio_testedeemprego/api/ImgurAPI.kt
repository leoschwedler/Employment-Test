package com.example.desafio_testedeemprego.api

import com.example.desafio_testedeemprego.model.ResponseAPI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImgurAPI {

    @GET("gallery/search/")
    suspend fun searchImg(@Query("q") q: String): Response <ResponseAPI>

}