package com.example.boletosmovil.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.boletosmovil.data.local.entity.boletosEntity
import com.example.boletosmovil.data.local.entity.respuestaEntity

@Dao
interface respuestaDao {


    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insert (confCaja: respuestaEntity)

    @Query(value = "SELECT * FROM respuesta WHERE id = 1")
    fun getRespuesta(): respuestaEntity

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertBoletos (boleto: boletosEntity)

    @Query(value = "SELECT * FROM boletos WHERE id = 1")
    fun getRespuestaB(): boletosEntity



}