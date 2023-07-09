package com.example.boletosmovil.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.example.boletosmovil.navegation.Screen
import com.example.boletosmovil.ui.Evento.eventoScreen


@Composable
fun homeScreen(  navController: NavHostController) {
    val isDrawerOpen = remember { mutableStateOf(false) }
    val selectedItem = remember { mutableStateOf("") }
    val navItems = listOf(

        Screen.homeScreen ,
        Screen.eventoScreen,
    )
    Surface(color = MaterialTheme.colorScheme.background) {
        Row(modifier = Modifier.fillMaxSize()) {

            Drawer(
                isOpen = isDrawerOpen.value,
                onClose = { isDrawerOpen.value = false },
                menuItems = navItems,
                onItemClick = { route ->
                    navController.navigate(route)
                    isDrawerOpen.value = false
                }
            )
            Content(
                isMenuOpen = isDrawerOpen.value,
                onMenuClick = { isDrawerOpen.value = true },
                navController
            )
        }
    }
}

@Composable
fun Drawer(
    isOpen: Boolean,
    onClose: () -> Unit,
    menuItems: List<Screen>,
    onItemClick: (String) -> Unit
) {
    if (isOpen) {
        Column(
            modifier = Modifier
                .width(240.dp)
                .fillMaxHeight()
                .padding(start = 16.dp),
        ) {
            menuItems.forEach { menuItem ->
                DrawerMenuItem(
                    text = menuItem.title,
                    onItemClick = {
                        onClose()
                        onItemClick(menuItem.route)
                    }
                )
            }
        }
    }
}

@Composable
fun Content(isMenuOpen: Boolean, onMenuClick: () -> Unit, navController: NavHostController) {

    val navItems = listOf(

        Screen.homeScreen ,
        Screen.eventoScreen,
        )
    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(
            onClick = onMenuClick,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null
            )
        }
        if (isMenuOpen) {
            Drawer(onClose = {}, onItemClick = {}, isOpen = false, menuItems = navItems  )
        }
        Text(
            text = "Contenido principal",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun DrawerMenuItem(
    text: String,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onItemClick() }
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}
