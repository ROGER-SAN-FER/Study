package com.example.study.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.study.data.local.entity.Modulo
import com.example.study.data.local.entity.Tarea
import com.example.study.ui.theme.StudyTheme
//import kotlin.Long.equals
import kotlin.collections.find
import kotlin.let
import kotlin.text.orEmpty

/**
 * Pantalla para actualizar una tarea existente en la aplicación.
 *
 * @param navController Controlador de navegación para manejar la navegación entre pantallas.
 * @param study2ViewModel ViewModel que maneja la lógica de negocio y la manipulación de datos.
 * @param moduloId Identificador del módulo al que pertenece la tarea.
 * @param tareaId Identificador único de la tarea que se desea actualizar.
 */
@Composable
fun ViewActualizarTarea(
    navController: NavHostController,
    studyViewModel: StudyViewModel,
    moduloId: Long,
    tareaId: Long
) {
    // Efecto lanzado para cargar los módulos al inicio de la pantalla
    LaunchedEffect (Unit) {
        studyViewModel.cargarModulos()
    }

    // Obtener la lista de módulos desde el ViewModel
    /*val modulos by studyViewModel.modulosFlow.collectAsState()
    val tareaSeleccionada by rememberSaveable (moduloId, tareaId) {
        Modulo.tareas?.find { Tarea.id equals tareaId }
    }*/

    val tareas by studyViewModel.tareasFlow.collectAsState()
    val tareaSeleccionada = tareas.find { it.id == tareaId }


    // Variables de estado para manejar los datos de la tarea a actualizar
    /*var nombreTarea by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(Tarea.nombreTarea.orEmpty()) }
    var fechaVencimiento by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(com.example.study2.model.Tarea.fechaVencimiento.orEmpty()) }
    var detalles by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(com.example.study2.model.Tarea.detalles.orEmpty()) }
    */
    val context = LocalContext.current
    var nombreTarea by remember { mutableStateOf(tareaSeleccionada?.nombreTarea ?: "") }
    var fechaVencimiento by remember { mutableStateOf(tareaSeleccionada?.fechaVencimiento ?: "") }
    var detalles by remember { mutableStateOf(tareaSeleccionada?.detalles ?: "") }

    // Estructura de la pantalla
    Scaffold(bottomBar = { MyBottomAppBar() }) { paddingValues ->
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(androidx.compose.foundation.rememberScrollState())
        ) {
            androidx.compose.foundation.layout.Column(
                horizontalAlignment = androidx.compose.ui.Alignment.Companion.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                InsertarTextoTitulo("ACTUALIZAR TAREA")

                InsertarTextFieldPersonalizado(
                    estado = nombreTarea,
                    onValueChange = { nombreTarea = it },
                    label = "Tarea",
                    placeHolder = "Tarea",
                    icono = Icons.Rounded.Create,
                    modifier = Modifier.padding(bottom = 28.dp)
                )

                InsertarTextFieldCalendario(
                    context,
                    fechaVencimiento,
                    { fechaVencimiento = it },
                    studyViewModel
                )

                InsertarTextFieldPersonalizado(
                    estado = detalles,
                    onValueChange = { detalles = it },
                    label = "Detalles",
                    placeHolder = "Detalles",
                    icono = Icons.Rounded.Menu,
                    modifier = Modifier.padding(bottom = 28.dp)
                )

                // Botón para actualizar la tarea
                /*ElevatedButton(
                    onClick = {
                        tareaSeleccionada?.let { tarea ->
                            val moduloAsociado = modulos.find { Modulo.id equals moduloId }
                            com.example.study2.ui.viewmodel.Study2ViewModel.actualizarTarea(
                                com.example.study2.model.Tarea(
                                    com.example.study2.model.Tarea.nombreTarea = nombreTarea,
                                    fechaVencimiento = fechaVencimiento,
                                    detalles = detalles,
                                    modulo = moduloAsociado!!
                                )
                            )
                        }
                        navController.navigate("ViewTareasPendientes")
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .height(48.dp)
                ) {
                    Text("ACTUALIZAR", color = Color.Black)
                }*/
                ElevatedButton(
                    onClick = {
                        // Solo si existe la tareaSeleccionada
                        tareaSeleccionada?.let { tarea ->
                            // Copiamos la tarea con los nuevos valores
                            val tareaActualizada = tarea.copy(
                                nombreTarea = nombreTarea,
                                fechaVencimiento = fechaVencimiento,
                                detalles = detalles
                                // Si deseas permitir cambiar el moduloId, tendrías que exponer
                                // esa lógica también.
                            )
                            // Llamamos al método de actualización en el ViewModel
                            studyViewModel.actualizarTarea(tareaActualizada)
                        }
                        // Navegamos de vuelta a la lista de tareas
                        navController.navigate("ViewTareasPendientes")
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .height(48.dp)
                ) {
                    Text("ACTUALIZAR", color = Color.Black)
                }
            }
        }
    }
}

/**
 * Vista previa de la pantalla de actualización de tarea.
 */
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun VistaPreviaViewActualizar() {
    StudyTheme {
        val navController = rememberNavController()
        val study2ViewModel: StudyViewModel = hiltViewModel()
        ViewActualizarTarea(navController, study2ViewModel, 0L, 0L)
    }
}
