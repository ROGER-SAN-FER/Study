package com.example.study

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.study.data.local.ModulosDatabase
import com.example.study.ui.theme.StudyTheme
import com.example.study.ui.view.Routes
import com.example.study.ui.view.StudyViewModel
import com.example.study.ui.view.ViewNuevaTarea
import com.example.study.ui.view.ViewNuevoModulo
import com.example.study.ui.view.ViewPrincipal
import com.example.study.ui.view.ViewTareasPendientes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //private lateinit var database: ModulosDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            //database = ModulosDatabase.getDatabase(this)
            StudyTheme {
                Study(/*database*/)
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
fun Study(/*database: ModulosDatabase*/) {
    //val context = LocalContext.current
    val navController = rememberNavController()
    val studyViewModel: StudyViewModel = hiltViewModel()

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
                studyViewModel
            )
        }
        composable(Routes.ViewNuevoModulo.route) { ViewNuevoModulo(navController, studyViewModel) }
        composable(Routes.ViewNuevaTarea.route) { ViewNuevaTarea(navController, studyViewModel) }
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

