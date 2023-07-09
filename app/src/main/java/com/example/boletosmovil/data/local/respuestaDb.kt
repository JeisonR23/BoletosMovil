package com.example.boletosmovil.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.boletosmovil.data.local.dao.respuestaDao
import com.example.boletosmovil.data.local.entity.boletosEntity
import com.example.boletosmovil.data.local.entity.respuestaEntity


@Database(
    entities = [respuestaEntity::class, boletosEntity::class],
    exportSchema = false,
    version  = 2
)
abstract class respuestaDb: RoomDatabase() {

    abstract val respuestaDao: respuestaDao
}

