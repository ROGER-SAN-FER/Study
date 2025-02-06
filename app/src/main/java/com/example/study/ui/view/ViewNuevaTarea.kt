package com.example.study.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.study.ui.theme.StudyTheme

//import com.example.study2.ui.theme.StudyTheme
//import com.example.study2.ui.viewmodel.Study2ViewModel

/**
 * Un composable que representa la pantalla para agregar una nueva tarea.
 *
 * @param navController El NavHostController para la navegación.
 * @param tareasViewModel El ViewModel que se utiliza para gestionar las tareas y los módulos.
 * @param modifier El modificador que se aplica a la pantalla.
 */
@Composable
fun ViewNuevaTarea(
    navController: NavHostController,
    tareasViewModel: TareasViewModel,
    modifier: Modifier = Modifier
) {
    //val modulos by studyViewModel.modulos.collectAsState()
    //var moduloSeleccionado by remember { mutableStateOf<com.example.study2.model.Modulo?>(null) }
    var tarea by rememberSaveable { mutableStateOf("") }
    var vencimiento by rememberSaveable { mutableStateOf("") }
    var detalle by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current


//    LaunchedEffect(Unit) {
//        com.example.study2.ui.viewmodel.Study2ViewModel.cargarModulos()
//    }

    Scaffold(
        bottomBar = {
            MyBottomAppBar()
        }
    ) { it ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                horizontalAlignment = Alignment.Companion.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "AGREGA UNA NUEVA TAREA:",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Companion.SemiBold,
                    modifier = Modifier
                        .padding(16.dp)
                )


                /*MyExposedDropDownMenuParaNuevaTarea(
                    listaModulos = modulos,
                    onModuloSeleccionado = { modulo ->
                        moduloSeleccionado = modulo
                    },
                    modifier = Modifier.padding(bottom = 28.dp)
                )*/

                InsertarTextFieldPersonalizado(
                    estado = tarea,
                    onValueChange = { tarea = it },
                    label = "Tarea",
                    placeHolder = "Tarea",
                    singleLine = true,
                    icono = Icons.Rounded.Create,
                    modifier = Modifier
                        .padding(bottom = 28.dp)
                        .width(300.dp)
                )

                InsertarTextFieldCalendario(
                    context,
                    vencimiento,
                    { vencimiento = it },
                    tareasViewModel
                )

                InsertarTextFieldPersonalizado(
                    estado = detalle,
                    onValueChange = { detalle = it },
                    label = "Detalles (opcional)",
                    placeHolder = "Detalles (opcional)",
                    singleLine = true,
                    icono = Icons.Rounded.Menu,
                    modifier = Modifier
                        .padding(bottom = 28.dp)
                        .width(300.dp)
                )

                InsertarBotonElevadoIcono(
                    text = "AGREGAR",
                    icono = Icons.Default.Add,
                    onClick = {
//                        com.example.study2.ui.viewmodel.Study2ViewModel.agregarTarea(
//                            nombreTarea = tarea,
//                            fechaVencimiento = vencimiento,
//                            detalles = detalle,
//                            completado = false,
//                            modulo = moduloSeleccionado!!
//                        )
//                        NavController.navigate("ViewTareasPendientes")
                    },
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .height(48.dp)
                        .width(200.dp)
                )

                InsertarImagenInternet(
                    "https://i.pinimg.com/736x/0f/9b/56/0f9b56ce3bdf35a271d483cfd635019e.jpg"
                )
            }
        }
    }
}

/**
 * Vista previa del composable [ViewNuevaTarea].
 */
@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Vista Previa ViewCentral"
)
fun VistaPreviaViewNuevaTarea() {
    StudyTheme {
        val navController = rememberNavController()
        val tareasViewModel: TareasViewModel = TareasViewModel()// =
        //    androidx.hilt.navigation.compose.hiltViewModel()
        ViewNuevaTarea(navController, tareasViewModel = tareasViewModel)
    }
}