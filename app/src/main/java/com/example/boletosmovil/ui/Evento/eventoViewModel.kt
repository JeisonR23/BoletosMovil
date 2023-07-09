package com.example.boletosmovil.ui.Evento

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
val tipoAsiento: String = ""
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

    private val uiStateBoleto = MutableStateFlow(boletoUiState())
    val _uiStateBoleto: StateFlow<boletoUiState> = uiStateBoleto.asStateFlow()

    init {
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

}