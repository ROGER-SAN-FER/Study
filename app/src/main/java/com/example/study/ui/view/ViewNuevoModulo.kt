package com.example.study.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AddLink
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.study.data.local.ModulosDatabase
import com.example.study.data.local.dao.ModulosTareasDao
import com.example.study.data.local.entity.Modulo
import com.example.study.data.local.entity.Tarea
import com.example.study.ui.theme.StudyTheme
//import com.example.study2.ui.theme.StudyTheme
import kotlin.text.isNotEmpty
import kotlin.text.toIntOrNull

/**
 * Composable que representa la vista para agregar un nuevo módulo.
 * Permite al usuario ingresar un nombre, cantidad de horas, prioridad e imagen del módulo.
 *
 * @param navController Controlador de navegación para cambiar de pantalla.
 * @param modifier Modificador opcional para personalización del diseño.
 */
@Composable
fun ViewNuevoModulo(
    navController: NavHostController,
    database: ModulosDatabase,
    modifier: Modifier = Modifier
) {
    //val study2ViewModel: com.example.study2.ui.viewmodel.Study2ViewModel =
   //     androidx.hilt.navigation.compose.hiltViewModel()
    var modulo by rememberSaveable { mutableStateOf("") }
    var totalHoras by rememberSaveable { mutableStateOf("") }
    var prioridad by rememberSaveable { mutableStateOf(0) }
    var imagenEnlace by rememberSaveable { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            MyBottomAppBar()
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.Companion.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Título principal
            Text(
                text = "AGREGA UN NUEVO MÓDULO:",
                fontSize = 24.sp,
                fontWeight = FontWeight.Companion.SemiBold,
                modifier = Modifier.padding(vertical = 32.dp)
            )

            // Imagen de vista previa del módulo
            if (imagenEnlace.isNotEmpty()) {
                InsertarImagenInternet(
                    imagenEnlace,
                    modifier = Modifier.size(200.dp)
                )
            } else {
                InsertarImagenInternet(
                    "https://www.peru.travel/Contenido/Uploads/llamas_637662916836413647.jpg",
                    modifier = Modifier.size(200.dp)
                )
            }

            // Campo de texto para el nombre del módulo
            InsertarTextFieldPersonalizado(
                estado = modulo,
                onValueChange = { modulo = it },
                label = "Nuevo módulo",
                placeHolder = "Nuevo módulo",
                icono = Icons.Default.AddCircle,
                modifier = Modifier.padding(top = 28.dp, bottom = 28.dp)
            )

            // Campo de texto para la cantidad total de horas
            InsertarTextFieldPersonalizado(
                estado = totalHoras,
                onValueChange = { totalHoras = it },
                label = "Horas totales del módulo",
                placeHolder = "Horas totales del módulo",
                icono = Icons.Default.Numbers,
                modifier = Modifier.padding(bottom = 28.dp)
            )

            // Campo de texto para ingresar una URL de imagen
            InsertarTextFieldPersonalizado(
                estado = imagenEnlace,
                onValueChange = { imagenEnlace = it },
                label = "Cargar imagen desde URL",
                placeHolder = "Cargar imagen desde URL",
                icono = Icons.Default.AddLink,
                modifier = Modifier.padding(bottom = 28.dp)
            )

            // Slider para seleccionar la prioridad del módulo
            SimpleContinuousSlider(
                modifier = Modifier.width(300.dp),
                selection = prioridad,
                onValueChangue = { prioridad = it },
                onValueChangeFinished = { prioridad = prioridad }
            )

            // Botón para agregar el módulo
            ElevatedButton(
                onClick = {
                    val horas = totalHoras.toIntOrNull() ?: 0
                    val imagen = if (imagenEnlace.isNotEmpty()) {
                        imagenEnlace
                    } else {
                        "https://www.peru.travel/Contenido/Uploads/llamas_637662916836413647.jpg"
                    }
//                    Study2ViewModel.guardarModulo(
//                        modulo,
//                        horas,
//                        imagen,
//                        prioridad
//                    )
                    navController.navigate("ViewTareasPendientes")
                },
                modifier = Modifier
                    .padding(top = 32.dp)
                    .width(200.dp)
            ) {
                Text(text = "AGREGAR", color = Color.Companion.Black)
            }
        }
    }
}

/**
 * Vista previa de la pantalla de nuevo módulo.
 */
@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Vista Previa ViewCentral"
)
fun VistaPreviaViewNuevoModulo() {
    StudyTheme {
        val navController = rememberNavController()
        // Implementación fake de ModulosDatabase para el preview:
        val fakeDatabase = object : ModulosDatabase() {
            override fun modulosTareasDao(): ModulosTareasDao {
                return object : ModulosTareasDao {
                    override suspend fun obtenerTodosModulos(): List<Modulo> = emptyList()
                    override suspend fun insertarModulo(modulo: Modulo) { }
                    override suspend fun eliminarModulo(modulo: Modulo) { }

                    override suspend fun obtenerTodasTareas(): List<Tarea> = emptyList()
                    override suspend fun insertarTarea(tarea: Tarea) { }
                    override suspend fun actualizarTarea(tarea: Tarea) { }
                    override suspend fun eliminarTarea(tarea: Tarea) { }
                }
            }

            override fun clearAllTables() {
                TODO("Not yet implemented")
            }

            override fun createInvalidationTracker(): InvalidationTracker {
                TODO("Not yet implemented")
            }

            override fun createOpenHelper(config: DatabaseConfiguration): SupportSQLiteOpenHelper {
                TODO("Not yet implemented")
            }
        }

        ViewNuevoModulo(navController, fakeDatabase)
    }
}
