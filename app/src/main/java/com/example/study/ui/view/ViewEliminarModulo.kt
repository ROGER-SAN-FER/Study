package com.example.study.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.study.data.local.entity.Modulo
import com.example.study.ui.theme.StudyTheme
import kotlin.let
import kotlin.run
import kotlin.text.isNotEmpty

/**
 * Un composable que representa la pantalla para eliminar un módulo.
 *
 * @param study2ViewModel El ViewModel que se utiliza para gestionar los módulos.
 * @param modifier El modificador que se aplica a la pantalla.
 */
@Composable
fun ViewEliminarModulo(
    studyViewModel: StudyViewModel,
    modifier: Modifier = Modifier
) {
    val modulos by studyViewModel.modulosFlow.collectAsState()
    var moduloSeleccionado by remember { mutableStateOf<Modulo?>(null) }
    var mensajeConfirmacion by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        studyViewModel.cargarModulos()
    }

    Scaffold(
        bottomBar = {
            MyBottomAppBar()
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                horizontalAlignment = Alignment.Companion.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                InsertarTextoTitulo("ELIMINA UN MÓDULO")

                var selectedModuloText by remember { mutableStateOf("") }

                MyExposedDropDownMenuParaEliminarModulo(
                    listaModulos = modulos,
                    moduloSeleccionado = { modulo ->
                        moduloSeleccionado = modulo
                        selectedModuloText = modulo.nombre
                    },
                    moduloSeleccionadoTextField = selectedModuloText,
                    limpiarSeleccion = {
                        selectedModuloText = ""
                    },
                    modifier = Modifier.padding(bottom = 28.dp)
                )

                InsertarBotonElevadoIcono(
                    text = "ELIMINAR",
                    icono = Icons.Default.Delete,
                    onClick = {
                        moduloSeleccionado?.let {
                            studyViewModel.eliminarModulo(it)
                            studyViewModel.cargarModulos() // Refrescar lista de módulos
                            mensajeConfirmacion = "Módulo eliminado con éxito."
                            moduloSeleccionado = null // Limpiar selección
                            selectedModuloText = "" // Limpiar el TextField
                        } ?: run {
                            mensajeConfirmacion = "Por favor selecciona un módulo para eliminar."
                        }
                    },
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .height(48.dp)
                        .width(200.dp)
                )
                if (mensajeConfirmacion.isNotEmpty()) {
                    Text(
                        text = mensajeConfirmacion,
                        color = Color.Companion.Red,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

/**
 * Vista previa del composable [ViewEliminarModulo].
 */
@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Vista Previa Eliminar Módulo"
)
fun VistaPreviaViewEliminarModulo() {
    StudyTheme {
        val studyViewModel: StudyViewModel = hiltViewModel()
        ViewEliminarModulo(studyViewModel = studyViewModel)
    }
}
