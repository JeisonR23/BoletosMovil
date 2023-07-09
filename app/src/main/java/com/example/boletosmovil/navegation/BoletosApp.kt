package com.example.boletosmovil.navegation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.boletosmovil.ui.Boletos.boletoScreen
import com.example.boletosmovil.ui.Boletos.compraScreen
import com.example.boletosmovil.ui.Evento.eventoScreen
import com.example.boletosmovil.ui.home.homeScreen


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MyApp(  ) {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController , startDestination = Screen.homeScreen.route) {
        composable(Screen.homeScreen.route) {
            homeScreen(navHostController)
        }
        composable(Screen.eventoScreen.route){
            eventoScreen(navHostController)
        }
        composable(Screen.boletoScreen.route){
            boletoScreen(navHostController)
        }
        composable(Screen.compraScreen.route){
            compraScreen(navHostController)
        }


    }
}

