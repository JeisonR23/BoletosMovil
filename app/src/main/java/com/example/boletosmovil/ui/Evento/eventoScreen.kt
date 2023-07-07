package com.example.boletosmovil.ui.Evento

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.boletosmovil.data.remote.dto.eventosDto

@Composable
fun eventoScreen(viewModel: eventoViewModel = hiltViewModel()) {


    val uiState = viewModel.uiState.collectAsState()
    println("UI State: $uiState")
    LazyColumn {
        items(uiState.value.evento) {

                evento -> EventoRow(evento = evento)
        }
    }


}

@Composable
fun EventoRow(evento: eventosDto) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Nombre Evento: ${evento.nombreEvento}")
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Fecha: ${evento.fecha}")
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Descripcion: ${evento.descripcion}")
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Ubicacion: ${evento.ubicacion}")
    }
}
