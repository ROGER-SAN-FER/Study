package com.example.study

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.study.ui.theme.StudyTheme
import com.example.study.ui.view.Routes
import com.example.study.ui.view.TareasViewModel
import com.example.study.ui.view.ViewNuevaTarea
import com.example.study.ui.view.ViewNuevoModulo
import com.example.study.ui.view.ViewPrincipal
import com.example.study.ui.view.ViewTareasPendientes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            StudyTheme {
                Study()
            }
        }
    }
}

/**
 * Función composable que configura la navegación y las pantallas de la aplicación.
 *
 * @param navController El NavHostController que gestiona la navegación entre pantallas.
 * @param study2ViewModel El ViewModel que proporciona los datos a las diferentes pantallas.
 */
@Composable
fun Study() {
    val navController = rememberNavController()
    val tareasViewModel = TareasViewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.ViewPrincipal.route
    ) {
        composable(Routes.ViewPrincipal.route) { ViewPrincipal(navController, false) }
        //composable(Routes.ViewInicioSesion.route) { ViewInicioSesion(navController) }
        //composable(Routes.ViewRegistro.route) { ViewRegistro(navController) }
        composable(Routes.ViewTareasPendientes.route) {
            ViewTareasPendientes(
                navController,
                tareasViewModel
            )
        }
        composable(Routes.ViewNuevoModulo.route) { ViewNuevoModulo(navController) }
        composable(Routes.ViewNuevaTarea.route) { ViewNuevaTarea(navController, tareasViewModel) }
//        composable(Routes.ViewDashboard.route) { ViewDashboard() }
//        composable(Routes.ViewEliminarModulo.route) { ViewEliminarModulo(study2ViewModel) }
//        composable(
//            route = "ViewActualizarTarea/{moduloId}/{tareaId}", // Ruta con parámetros
//            arguments = listOf(
//                navArgument("moduloId") {
//                    type = NavType.LongType
//                }, // Argumento para el ID del módulo
//                navArgument("tareaId") {
//                    type = NavType.LongType
//                } // Argumento para el ID de la tarea
//            )
//        ) { backStackEntry ->
//            val moduloId =
//                backStackEntry.arguments?.getLong("moduloId") ?: 0L // Obtener ID del módulo
//            val tareaId =
//                backStackEntry.arguments?.getLong("tareaId") ?: 0L // Obtener ID de la tarea
//            ViewActualizarTarea(
//                navController = navController,
//                moduloId = moduloId,
//                study2ViewModel = study2ViewModel,
//                tareaId = tareaId
//            )
//        }
    }
}

