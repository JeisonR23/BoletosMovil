package com.example.boletosmovil.ui.Boletos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.boletosmovil.data.local.entity.boletosEntity
import com.example.boletosmovil.data.remote.dto.boletosDto
import com.example.boletosmovil.ui.Evento.eventoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun compraScreen(
    navHostController: NavHostController, viewModel: eventoViewModel= hiltViewModel()
){


    Column() {
        TopAppBar(
            title = { Text(text = "Boleto") },
            navigationIcon = {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )

        val uiStateB = viewModel.uiStateB.collectAsState()
        LazyColumn {
            items(uiStateB.value.boleto) {

                    boleto -> compraRow(boleto = boleto, navController = navHostController)
            }
        }

    }

}

@Composable
fun compraRow(navController: NavHostController, boleto: boletosDto, viewModel: eventoViewModel = hiltViewModel()) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Surface(color = MaterialTheme.colorScheme.onPrimary) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Cantidad Boletos ${boleto.cantidadBoletos}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Precio: ${boleto.price}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Tipo Asiento: ${boleto.tipoAsiento}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.padding(16.dp))
                    Button(
                        onClick = {
                            viewModel.Comprar(boleto.boletoId)
                            viewModel.GuardarB(respuesta = boletosEntity(
                                response = boleto.boletoId
                            )
                            )
                            navController.navigate("compraScreen")
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


