package com.example.desafio_testedeemprego.model

data class ResponseAPI(
    val `data`: List<Data>,
    val status: Int,
    val success: Boolean
)