package com.example.study.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.study.ui.theme.StudyTheme
//import androidx.navigation.compose.rememberNavController
//import com.example.study2.ui.theme.StudyTheme
import kotlinx.coroutines.delay


/**
 * Un composable que representa la pantalla principal de la aplicación, mostrando el logo,
 * una animación de agenda y la información del desarrollador.  Después de un tiempo, navega a la
 * pantalla de inicio de sesión.
 *
 * @param navController El NavHostController para la navegación.
 * @param verPreview Indica si se está mostrando la vista previa (true) o la aplicación real (false).
 * @param modifier El modificador que se aplica a la pantalla.
 */
@Composable
fun ViewPrincipal(
    navController: NavHostController,
    verPreview: Boolean,
    modifier: Modifier = Modifier
) {
    var isReady by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isReady = true
        delay(2000L)
        navController.navigate("ViewTareasPendientes")
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        if (isReady) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Companion.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .semantics(mergeDescendants = true) {}
                ) {
                    InsertarImagenLogo()
                    Text(
                        text = "tudy",
                        color = Color.Companion.Black,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .semantics { heading() }
                    )
                }
                InsertarAgendaAnimada(verPreview)
            }

            Text(
                text = "BY ROGER ROY SANCHEZ FERNANDEZ",
                //color = Color.White,
                fontWeight = FontWeight.Companion.Bold,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.Companion.BottomCenter)
                    .padding(bottom = 4.dp) // Añadir un pequeño espacio al final
                    .semantics {
                        contentDescription =
                            "Aplicación desarrollada por Roger Roy Sánchez Fernández"
                    }
            )
        }
    }
}

/**
 * Vista previa del composable [ViewPrincipal].
 */
@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Pantalla Principal"
)
fun VistaPreviaViewPrincipal() {
    StudyTheme {
        val navController = rememberNavController()
        ViewPrincipal(navController, verPreview = true)
    }
}