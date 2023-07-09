package com.example.boletosmovil.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "respuesta")
data class respuestaEntity (
    @PrimaryKey(autoGenerate = false)
    val id : Int = 1,
    val response : Long
)