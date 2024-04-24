package com.example.desafio_testedeemprego.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request()
            .newBuilder()

        val request = requestBuilder
            .addHeader("Authorization", "Client-ID 1f1ef317033de1e").build()

        return chain.proceed(request)
    }
}