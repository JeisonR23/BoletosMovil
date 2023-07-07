package com.example.boletosmovil.di

import android.content.Context
import com.example.boletosmovil.data.remote.api.boletosApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    val baseUrl = "http://10.0.0.100:8081"
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesApi(): boletosApi {
        return Retrofit.Builder()
            .baseUrl("http://10.0.0.100:8081/")
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .build()
            .create(boletosApi::class.java)
    }

}