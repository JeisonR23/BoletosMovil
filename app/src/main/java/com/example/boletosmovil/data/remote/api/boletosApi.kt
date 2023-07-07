package com.example.boletosmovil.data.remote.api

import com.example.boletosmovil.data.remote.dto.eventosDto
import retrofit2.Response
import retrofit2.http.GET

interface boletosApi {

    @GET("evento/list/evento")
    suspend fun getEventos() : Response<List<eventosDto>>

}