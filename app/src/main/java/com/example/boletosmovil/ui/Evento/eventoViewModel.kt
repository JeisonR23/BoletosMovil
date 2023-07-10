package com.example.boletosmovil.ui.Evento

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boletosmovil.data.local.entity.boletosEntity
import com.example.boletosmovil.data.local.entity.respuestaEntity
import com.example.boletosmovil.data.local.repository.respuestaRepository
import com.example.boletosmovil.data.remote.dto.boletosDto
import com.example.boletosmovil.data.remote.dto.eventosDto
import com.example.boletosmovil.data.repository.eventoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

data class eventoListUiState(
    val evento: List<eventosDto> = emptyList()
)

data class boletoUiState(
    val boletoId: Long = 0L,
    val cantidadBoletos: Int = 0,
    val price: Double = 0.0,
    val tipoAsiento: String = "",
    val evento: eventosDto = eventosDto(
       0L,
        "",
        "",
        "",
        ""
    )
)

data class boletosListUiState(
    val boleto: List<boletosDto> = emptyList()
)

@HiltViewModel
class eventoViewModel @Inject constructor(
    val repository: eventoRepository,
    val respuestas: respuestaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(eventoListUiState())
    val uiState: StateFlow<eventoListUiState> = _uiState.asStateFlow()

    private val _uiStateB = MutableStateFlow(boletosListUiState())
    val uiStateB: StateFlow<boletosListUiState> = _uiStateB.asStateFlow()

    private val _uiStateBoleto = MutableStateFlow(boletoUiState())
    val uiStateBoleto: StateFlow<boletoUiState> = _uiStateBoleto.asStateFlow()

    val cantidadBoletos = mutableStateOf(0)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val respuesta = respuestas.getRespuestaB()
            val resE = respuestas.getRespuesta()
            println(resE)
            try {
                val response = repository.getBoletosById(respuesta.response)
                val boletos = response.body()
                _uiStateBoleto.value = _uiStateBoleto.value.copy(
                    respuesta.response,
                    cantidadBoletos = boletos?.cantidadBoletos ?: 0,
                    price = boletos?.price ?: 0.0,
                    tipoAsiento = boletos?.tipoAsiento ?: "",
                    evento= eventosDto(
                        eventoId = resE.response,
                        "",
                        "",
                        "",
                        ""
                    )


                )
            } catch (e: Exception) {
                _uiStateBoleto.value = _uiStateBoleto.value.copy(
                    boletoId = 0L,
                    cantidadBoletos = 0,
                    price = 0.0,
                    tipoAsiento = "",
                    evento= eventosDto(
                        0,
                        "" ,
                        "",
                        "",
                        ""
                    )
                )

            }
        }
        viewModelScope.launch {
            _uiState.getAndUpdate {
                try {
                    it.copy(evento = repository.getEvento())
                } catch (io: IOException) {
                    it.copy(evento = emptyList())
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            val respuesta = respuestas.getRespuesta()
            try {
                val boletos = repository.getBoletos(respuesta.response)
                _uiStateB.getAndUpdate { currentState ->
                    currentState.copy(boleto = boletos)
                }
            } catch (io: IOException) {
                _uiStateB.getAndUpdate { currentState ->
                    currentState.copy(boleto = emptyList())
                }
            }
        }
    }

    fun Reservar(evento: Long) {
        viewModelScope.launch {
            repository.getBoletos(evento)
        }
    }

    fun Comprar(boleto: Long) {
        viewModelScope.launch {
            repository.getBoletosById(boleto)
        }
    }

    fun GuardarB(respuesta: boletosEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            respuestas.insertBoletos(respuesta)
        }
    }

    fun Guardar(respuesta: respuestaEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            respuestas.insert(respuesta)
        }
    }


    suspend fun postBoleto(boleto: boletosDto, boletoid: Long) {
        try {
            repository.postBoletos(boleto, boletoid)
        } catch (e: Exception) {

        }
    }

    fun actualizarBoleto(boleto: boletosDto) {
        viewModelScope.launch(Dispatchers.IO) {
            val respuesta = respuestas.getRespuestaB()
            try {
                postBoleto(boleto, respuesta.response)
            } catch (e: Exception) {
                println("No se actuazliso")
            }
        }
    }


}