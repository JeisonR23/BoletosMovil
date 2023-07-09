package com.example.boletosmovil.data.remote.dto

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class boletosDto(
    val boletoId: Long,
    val cantidadBoletos: Int,
    val price: Double,
    val tipoAsiento: String

)
