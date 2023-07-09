package com.example.boletosmovil.ui.Evento

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.boletosmovil.data.local.entity.respuestaEntity
import com.example.boletosmovil.data.remote.dto.eventosDto


@Composable
fun eventoScreen(navController: NavHostController, viewModel: eventoViewModel = hiltViewModel()) {


    val uiState = viewModel.uiState.collectAsState()
    println("UI State: $uiState")
    LazyColumn {
        items(uiState.value.evento) {

                evento ->
            EventoRow(evento = evento, navController = navController)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventoRow(
    navController: NavHostController,
    evento: eventosDto,
    viewModel: eventoViewModel = hiltViewModel()
) {

    var respuesta2 = remember {
        0L
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Nombre Evento: ${evento.nombreEvento}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Id: ${evento.eventoId}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Fecha: ${evento.fecha}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Descripción: ${evento.descripcion}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Ubicación: ${evento.ubicacion}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                    Button(
                        onClick = {
                            viewModel.Reservar(evento.eventoId)
                            viewModel.Guardar(
                                respuesta = respuestaEntity(
                                    response = evento.eventoId
                                )
                            )

                            navController.navigate("beletoScreen")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Reservar")
                    }
                }
                Divider(color = Color.Blue, thickness = 1.dp)
            }
        }
    }
}


