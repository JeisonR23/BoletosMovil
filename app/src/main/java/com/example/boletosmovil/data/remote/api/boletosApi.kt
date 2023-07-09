package com.example.boletosmovil.data.remote.api

import com.example.boletosmovil.data.remote.dto.boletosDto
import com.example.boletosmovil.data.remote.dto.eventosDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface boletosApi {

    @GET("evento/list/evento")
    suspend fun getEventos() : Response<List<eventosDto>>

    @GET("boletos/evento/{eventoId}")
    suspend fun getBoletosByEvntos(@Path("eventoId") eventoId: Long): List<boletosDto>

    @GET("boletos//boleto/{id}")
    suspend fun getBoletosById(@Path("id") eventoId: Long): boletosDto


}