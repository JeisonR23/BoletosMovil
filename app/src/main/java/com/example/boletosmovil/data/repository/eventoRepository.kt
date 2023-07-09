package com.example.boletosmovil.data.repository

import com.example.boletosmovil.data.remote.api.boletosApi
import com.example.boletosmovil.data.remote.dto.boletosDto
import com.example.boletosmovil.data.remote.dto.eventosDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
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

    suspend fun getBoletosById(id: Long) : boletosDto
    {
        try {
            return api.getBoletosById(id)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getBoletos(comp: Long) : List<boletosDto>
    {
        try {
            val list: List<boletosDto> = api.getBoletosByEvntos(comp)
            return list
        } catch (e: Exception) {
            throw e
        }
    }


}