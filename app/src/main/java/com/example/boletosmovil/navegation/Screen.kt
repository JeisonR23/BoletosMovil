package com.example.boletosmovil.navegation


sealed class Screen (val route : String, val title : String){
    object  homeScreen : Screen("home","Inicio")

    object  eventoScreen : Screen("eventoScreen", "Evento")

    object  boletoScreen : Screen("beletoScreen", "Boleto")

    object  compraScreen : Screen("compraScreen", "Compra")
}