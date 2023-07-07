package com.example.boletosmovil.data.repository

import com.example.boletosmovil.data.remote.api.boletosApi
import com.example.boletosmovil.data.remote.dto.eventosDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class eventoRepository @Inject constructor(
    val api: boletosApi
) {

    suspend fun getEvento() : List<eventosDto>
    {
        return withContext(Dispatchers.IO){
            val response = api.getEventos()
            response.body()?: emptyList()
        }
    }

}