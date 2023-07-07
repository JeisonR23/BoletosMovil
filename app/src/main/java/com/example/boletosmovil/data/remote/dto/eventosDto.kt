package com.example.boletosmovil.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true  )
data class eventosDto(

    val eventoId: Long,
    val nombreEvento: String,
    val descripcion: String,
    val fecha: String,
    val ubicacion: String
)
