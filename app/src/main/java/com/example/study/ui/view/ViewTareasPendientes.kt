package com.example.study.ui.view


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.study.data.local.entity.Tarea
import com.example.study.ui.theme.StudyTheme
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.compose.rememberNavController
//import com.example.study2.ui.theme.StudyTheme
//import com.example.study2.ui.viewmodel.Study2ViewModel
//import kotlin.Boolean.not
import kotlin.collections.forEach

/**
 * Un composable que representa la pantalla de tareas pendientes, mostrando una lista de tareas
 * y opciones de filtrado.
 *
 * @param navController El NavHostController para la navegación.
 * @param study2ViewModel El ViewModel que se utiliza para gestionar las tareas y los módulos.
 */
@Composable
fun ViewTareasPendientes(
    navController: NavHostController,
    studyViewModel: StudyViewModel
) {
    val modulos by studyViewModel.modulosFlow.collectAsState()
    val tareas by studyViewModel.tareasFlow.collectAsState()
    var filtroSeleccionado by rememberSaveable { mutableStateOf("Ninguno") }
    val tareasPendientes = when (filtroSeleccionado) {
        "Primeros en vencer" -> tareas.filter { !it.completado }
            .sortedBy { it.fechaVencimiento }

        "Últimos en vencer" -> tareas.filter { !it.completado }
            .sortedByDescending { it.fechaVencimiento }

        "Prioridad de Módulo" -> tareas.filter { !it.completado }.sortedByDescending { tarea ->
            modulos.find { modulo -> modulo.id == tarea.moduloId }?.prioridad
        }

        else -> tareas.filter { !it.completado }
    }
    val mydrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    LaunchedEffect(Unit) {
        Log.d("MostrarTareas", "Cargando módulos y tareas...")
        //com.example.study2.ui.viewmodel.Study2ViewModel.cargarModulos()
    }
    MyModalDrawer(
        navController,
        drawerState = mydrawerState,
        contenido = {
            Scaffold(
                topBar = {
                    MyTopAppBarCenterImage(mydrawerState, navController)
                },
                floatingActionButton = {
                    MyFAB(navController)
                }
            ) { it ->
                Column(
                    horizontalAlignment = Alignment.Companion.CenterHorizontally,
                    modifier = Modifier
                        .padding(it)
                ) {
                    InsertarTextoTitulo(
                        texto = "Filtar por:",
                        modifier = Modifier
                            .padding(top = 4.dp, start = 8.dp)
                            .fillMaxWidth()
                            .align(Alignment.Companion.Start)
                    )

                    Row(//Para chips
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        LazyRow(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .padding(bottom = 16.dp)
                                .fillMaxWidth()
                        ) {
                            val chipList: List<String> = listOf(
                                "Ninguno",
                                "Primeros en vencer",
                                "Últimos en vencer",
                                "Prioridad de Módulo"
                            )
                            chipList.forEach { opcion ->
                                item {
                                    FilterChipExample(
                                        opcion = opcion,
                                        onFilterChange = { filtroSeleccionado = it },
                                        selectedFilter = filtroSeleccionado
                                    )
                                    Spacer(Modifier.size(5.dp))
                                }
                            }
                        }
                    }

                    HorizontalDivider(
                        color = Color.Companion.Gray,
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )

                    Spacer(Modifier.height(16.dp))

                    InsertarTextoTitulo(
                        texto = "TAREAS PENDIENTES",
                        style = MaterialTheme.typography.titleLarge
                    )

                    MyLazyColumn(
                        tareas = tareasPendientes,
                        studyViewModel = studyViewModel,
                        navController = navController,
                        modulos = modulos
                    )
                }//Column
            }//Scaffold
        }
    )
}

/**
 * Vista previa del composable [ViewTareasPendientes].
 */
@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Vista Previa ViewCentral"
)
fun VistaPreviaViewCentral() {
    StudyTheme {
        val navController = rememberNavController()
        val studyViewModel: StudyViewModel = hiltViewModel()
            //androidx.hilt.navigation.compose.hiltViewModel()
        ViewTareasPendientes(navController, studyViewModel)
    }
}