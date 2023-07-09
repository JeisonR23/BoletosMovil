package com.example.boletosmovil.data.local.repository

import com.example.boletosmovil.data.local.entity.boletosEntity
import com.example.boletosmovil.data.local.entity.respuestaEntity
import com.example.boletosmovil.data.local.respuestaDb
import com.example.boletosmovil.data.remote.api.boletosApi
import javax.inject.Inject

class respuestaRepository @Inject constructor(
    val respuesta: respuestaDb,
    val api : boletosApi

) {

    fun insert(entity: respuestaEntity) {
        respuesta.respuestaDao.insert(entity)
    }

    fun getRespuesta(): respuestaEntity {
        return respuesta.respuestaDao.getRespuesta()
    }

    fun insertBoletos(entity: boletosEntity) {
        respuesta.respuestaDao.insertBoletos(entity)
    }

    fun getRespuestaB(): boletosEntity {
        return respuesta.respuestaDao.getRespuestaB()
    }
}