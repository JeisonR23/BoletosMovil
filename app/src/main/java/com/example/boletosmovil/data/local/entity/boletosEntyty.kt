package com.example.boletosmovil.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boletos")
data class boletosEntity (
    @PrimaryKey(autoGenerate = false)
    val id : Int = 1,
    val response : Long
)