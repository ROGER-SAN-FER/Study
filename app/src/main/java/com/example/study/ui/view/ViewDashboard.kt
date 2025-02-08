package com.example.study.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Un composable que representa la pantalla del Dashboard, mostrando un indicador de carga circular.
 * Actualmente, solo muestra un indicador de carga.  En una implementación real, aquí se mostraría
 * la información del dashboard.
 */
@Composable
fun ViewDashboard() {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}