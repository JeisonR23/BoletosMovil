package com.example.boletosmovil.ui.Evento

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boletosmovil.data.remote.dto.eventosDto
import com.example.boletosmovil.data.repository.eventoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


data class eventoListUiState(
    val evento : List<eventosDto> = emptyList()
)



@HiltViewModel
class  eventoViewModel @Inject constructor(
    val repository: eventoRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(eventoListUiState())
    val  uiState : StateFlow<eventoListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.getAndUpdate {
                try {
                    it.copy(evento = repository.getEvento())
                }catch (io: IOException){
                    it.copy(evento = emptyList())
                }
            }
        }
    }


}