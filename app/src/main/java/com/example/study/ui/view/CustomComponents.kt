package com.example.study.ui.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dotlottie.dlplayer.Mode
//import com.example.study.data.model.Modulo
//import androidx.navigation.NavController
//import androidx.navigation.NavHostController
//import coil.compose.AsyncImage
//import coil.request.ImageRequest
//import com.dotlottie.dlplayer.Mode
//import com.example.study2.R
//import com.example.study2.model.Modulo
//import com.example.study2.model.Tarea
//import com.example.study2.ui.theme.shapes
//import com.example.study2.ui.viewmodel.Study2ViewModel
import com.lottiefiles.dotlottie.core.compose.runtime.DotLottieController
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import kotlinx.coroutines.launch
import kotlin.collections.forEach
import kotlin.math.roundToInt
import com.example.study.R
import com.example.study.data.local.entity.Modulo
import com.example.study.data.local.entity.Tarea
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import com.example.study.LocalActivity


/**
 * Muestra un título de texto con un tamaño de fuente predefinido.
 *
 * @param texto El contenido del texto a mostrar.
 * @param modifier Modificador opcional para personalizar el diseño.
 * @param style Estilo de texto opcional.
 */
@Composable
fun InsertarTextoTitulo(
    texto: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        text = texto,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        style = style,
        modifier = modifier.padding(bottom = 28.dp)
    )
}

/**
 * Un composable que muestra un menú desplegable con opciones de módulos.
 *
 * @param listaModulos Lista de módulos disponibles.
 * @param moduloSeleccionado Callback para manejar la selección de un módulo.
 * @param modifier Modificador opcional.
 * @param moduloSeleccionadoTextField Texto del módulo seleccionado.
 * @param limpiarSeleccion Callback para limpiar la selección actual.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyExposedDropDownMenuParaEliminarModulo(
    listaModulos: List<Modulo>,
    moduloSeleccionado: (Modulo) -> Unit,
    modifier: Modifier = Modifier,
    moduloSeleccionadoTextField: String,
    limpiarSeleccion: () -> Unit // Callback para limpiar el estado
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .width(300.dp),
            readOnly = true,
            value = moduloSeleccionadoTextField,
            onValueChange = {},
            label = { Text("Selecciona un módulo") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.height(300.dp)
        ) {
            listaModulos.forEach { modulo ->
                DropdownMenuItem(
                    text = { Text(modulo.nombre, fontSize = 16.sp) },
                    onClick = {
                        moduloSeleccionado(modulo)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}


/**
 * Un composable que representa un campo de texto personalizado con un icono, etiqueta, marcador de posición y opciones de teclado.
 *
 * @param estado El valor actual del texto en el campo.
 * @param onValueChange La función lambda que se llama cuando el valor del texto cambia.  Recibe el nuevo valor como argumento.
 * @param label La etiqueta que se muestra encima del campo de texto.
 * @param placeHolder El texto de marcador de posición que se muestra dentro del campo de texto cuando está vacío.
 * @param singleLine Indica si el campo de texto debe ser de una sola línea (true) o multilínea (false). El valor predeterminado es true.
 * @param icono El icono que se muestra al principio del campo de texto.
 * @param keyboardOptions Las opciones de teclado que se utilizan para el campo de texto.  Por defecto, es un teclado de texto con la acción ImeAction.Next.
 * @param visualTransformation La transformación visual que se aplica al texto del campo. Por defecto, no se aplica ninguna transformación.
 * @param modifier El modificador que se aplica al campo de texto.
 */
@Composable
fun InsertarTextFieldPersonalizado(
    estado: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeHolder: String,
    singleLine: Boolean = true,
    icono: ImageVector,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier

) {
    TextField(
        value = estado,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = { Text(text = placeHolder) },
        singleLine = singleLine,
        leadingIcon = { Icon(icono, null) },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        modifier = modifier
            .width(300.dp)
    )
}

/**
 * Un composable que muestra un botón elevado con un ícono.
 *
 * @param text Texto a mostrar en el botón.
 * @param icono Ícono que se muestra junto al texto.
 * @param onClick Acción a ejecutar al hacer clic.
 * @param modifier Modificador opcional.
 * @param colors Colores personalizados para el botón.
 */
@Composable
fun InsertarBotonElevadoIcono(
    text: String,
    icono: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.elevatedButtonColors()
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier,
        colors = colors
    ) {
        Icon(imageVector = icono, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = Color.Black)
    }
}

/**
 * Un composable que representa un botón elevado con una imagen y texto.
 *
 * @param text El texto que se muestra en el botón.
 * @param imagen El recurso de imagen que se muestra en el botón.
 * @param onClick La función lambda que se llama cuando se hace clic en el botón.
 * @param modifier El modificador que se aplica al botón.
 * @param colors Los colores del botón. Por defecto, se utilizan los colores predeterminados para botones elevados.
 */
@Composable
fun InsertarBotonElevadoImagen(
    text: String,
    @DrawableRes imagen: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.elevatedButtonColors()
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier,
        colors = colors
    ) {
        Image(
            painterResource(imagen), contentDescription = null, modifier = Modifier
                .size(30.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = Color.Black)
    }
}

/**
 * Un composable que representa un campo de texto para seleccionar una fecha y hora.
 *
 * @param context El contexto de la aplicación.
 * @param vencimiento El valor actual de la fecha y hora seleccionada.
 * @param clickable La función lambda que se llama cuando se hace clic en el campo de texto. Recibe la fecha y hora seleccionada como argumento.
 * @param studyViewModel El ViewModel que se utiliza para seleccionar la fecha y hora.
 */
@Composable
fun InsertarTextFieldCalendario(
    context: Context,
    vencimiento: String,
    clickable: (String) -> Unit,
    studyViewModel: StudyViewModel
) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .clickable {
                studyViewModel.seleccionarFechaHora(context) { clickable(it) }
            }
    ) {
        TextField(
            value = vencimiento,
            onValueChange = {},
            label = { Text(text = "Fecha y hora de Vencimiento") },
            placeholder = { Text(text = "Fecha y hora de Vencimiento") },
            readOnly = true,
            enabled = false,
            leadingIcon = { Icon(Icons.Default.DateRange, null) },
            colors = TextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledIndicatorColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = MaterialTheme.colorScheme.onSurface,
                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier
                .width(300.dp)
                .padding(bottom = 28.dp)
                .focusable(false)
                .align(Alignment.Center)
        )
    }
}

/**
 * Un composable que representa un menú desplegable para seleccionar un módulo.
 *
 * @param listaModulos La lista de módulos que se muestran en el menú desplegable.
 * @param onModuloSeleccionado La función lambda que se llama cuando se selecciona un módulo. Recibe el módulo seleccionado como argumento.
 * @param modifier El modificador que se aplica al menú desplegable.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyExposedDropDownMenuParaNuevaTarea(
    listaModulos: List<Modulo>,
    onModuloSeleccionado: (Modulo) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedModuloText by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .width(300.dp),
            readOnly = true,
            value = selectedModuloText,
            onValueChange = {},
            label = { Text("Selecciona un módulo") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.height(300.dp)
        ) {
            listaModulos.forEach { modulo ->
                DropdownMenuItem(
                    text = { Text(modulo.nombre, fontSize = 16.sp) },
                    onClick = {
                        selectedModuloText = modulo.nombre
                        onModuloSeleccionado(modulo)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

/**
 * Un composable que representa un slider continuo para seleccionar la prioridad.
 *
 * @param selection El valor actual de la prioridad.
 * @param onValueChangue La función lambda que se llama cuando el valor de la prioridad cambia. Recibe el nuevo valor como argumento.
 * @param onValueChangeFinished La función lambda que se llama cuando el usuario termina de interactuar con el slider.
 * @param modifier El modificador que se aplica al slider.
 */
@Composable
fun SimpleContinuousSlider(
    selection: Int,
    onValueChangue: (Int) -> Unit,
    onValueChangeFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val range = 0f..10f
    //var selectionPrioridad by rememberSaveable { mutableStateOf(0) }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(Icons.Default.Star, contentDescription = null)
        Text("Prioridad: ")
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = selection.toString())
    }

    Slider(
        value = selection.toFloat(),
        valueRange = range,
        onValueChange = { newValue ->
            onValueChangue(newValue.toInt())
        },
        onValueChangeFinished = onValueChangeFinished,
        modifier = modifier
    )
}

/**
 * Un composable que muestra una imagen desde una URL.
 * @param link URL de la imagen.
 * @param modifier Modificador opcional para personalizar el diseño.
 */
@Composable
fun InsertarImagenInternet(link: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(link)
            .build(),
        contentDescription = "Imagen cargada desde internet",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

/**
 * Un composable que representa la barra inferior de navegación con botones para Moodle, Aules y Google.
 */
@Composable
fun MyBottomAppBar() {
    val context = LocalContext.current
    BottomAppBar(
        actions = {
            InsertarBotonMoodle(context, modifier = Modifier.weight(1f))
            InsertarBotonAules(context, modifier = Modifier.weight(1f))
            InsertarBotonGoogle(context, modifier = Modifier.weight(1f))
        },
    )
}

/**
 * Un composable que representa un botón para abrir Google en un navegador.
 *
 * @param context El contexto de la aplicación.
 * @param modifier El modificador que se aplica al botón.
 */
@Composable
fun InsertarBotonGoogle(context: Context, modifier: Modifier = Modifier) {
    IconButton(
        onClick = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.es/")
            )
            startActivity(context, intent, null)
        },
        modifier = modifier
    ) {
        InsertarImagen(
            imagen = R.drawable.google,
            contentDescription = "Google",
            modifier = Modifier
                .size(44.dp)
                .wrapContentSize(),
            contentScale = ContentScale.FillWidth
        )
    }
}

/**
 * Un composable que representa un botón para abrir Aules en un navegador.
 *
 * @param context El contexto de la aplicación.
 * @param modifier El modificador que se aplica al botón.
 */
@Composable
fun InsertarBotonAules(context: Context, modifier: Modifier = Modifier) {
    IconButton(
        onClick = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://aules.edu.gva.es/semipresencial/login/index.php")
            )
            startActivity(context, intent, null)
        },
        modifier = modifier
    ) {
        InsertarImagen(
            imagen = R.drawable.aules,
            contentDescription = "Aules",
            modifier = Modifier
                .size(44.dp)
                .wrapContentSize(),
            contentScale = ContentScale.FillWidth
        )
    }
}

/**
 * Un composable que representa un botón para abrir la aplicación Moodle o redirigir a la Play Store si no está instalada.
 *
 * @param context El contexto de la aplicación.
 * @param modifier El modificador que se aplica al botón.
 */
@Composable
fun InsertarBotonMoodle(context: Context, modifier: Modifier = Modifier) {
    IconButton(
        onClick = {
            val packageName = "com.moodle.moodlemobile" // Nombre real del paquete
            try {
                // Intentar abrir la aplicación si está instalada
                val intent = Intent().apply {
                    component = ComponentName(
                        packageName,
                        "com.moodle.moodlemobile.MainActivity" // Actividad a abrir
                    )
                }
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                // Si falla (la app no está instalada), redirigir a la Play Store
                val playStoreIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                )
                context.startActivity(playStoreIntent)
            }
        },
        modifier = modifier
    ) {
        InsertarImagen(
            imagen = R.drawable.moodle,
            contentDescription = "Moodle",
            modifier = Modifier
                .size(44.dp)
                .wrapContentSize(),
            contentScale = ContentScale.FillWidth
        )
    }
}

/**
 * Un composable que muestra una animación de agenda o una imagen de reemplazo en la vista previa.
 *
 * @param verPreview Indica si se debe mostrar la imagen de reemplazo (true) o la animación (false).
 */
@Composable
fun InsertarAgendaAnimada(verPreview: Boolean) {
    if (verPreview) {
        // Muestra una imagen de reemplazo en el Preview
        Image(
            painterResource(R.drawable.imagenagenda),
            contentDescription = "Animación de una agenda",
            modifier = Modifier.size(300.dp)
        )
    } else {//Se muestra la animación en el dispositivo
        //Controlador para la animación
        val dotLottieController = remember { DotLottieController() }
        DotLottieAnimation(
            source = DotLottieSource.Asset("agenda.json"), // url of .json or .lottie
            autoplay = true,
            loop = true,
            speed = 1f,
            useFrameInterpolation = false,
            playMode = Mode.FORWARD,
            controller = dotLottieController,
            modifier = Modifier
                .size(300.dp)
                .semantics {
                    contentDescription = "Animación de una agenda"
                    stateDescription = "Animación de una agenda"
                }
        )
    }
}

/**
 * Un composable que muestra el logotipo de la aplicación con una animación de elevación.
 */
@Composable
fun InsertarImagenLogo() {
    val elevationValue by rememberInfiniteTransition(label = "Imagen animada")
        .animateFloat(
            initialValue = 1.dp.value,
            targetValue = 8.dp.value,//
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000),
                repeatMode = RepeatMode.Reverse
            ),
            label = "Elevación de imagen"
        )
    Box(
        modifier = Modifier
            .height(124.dp)
            .width(85.dp)//Para evitar desplazamientos
    ) {
        InsertarImagen(
            imagen = R.drawable.s,
            contentDescription = "Logotipo de la app animado",
            modifier = Modifier
                .height(124.dp)
                .padding(end = 4.dp)
                .padding(elevationValue.dp),
        )
    }
}

/**
 * Un composable que representa un chip de filtro.
 *
 * @param opcion El texto que se muestra en el chip.
 * @param onFilterChange La función lambda que se llama cuando se hace clic en el chip. Recibe la opción seleccionada como argumento.
 * @param selectedFilter El filtro actualmente seleccionado.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipExample(opcion: String, onFilterChange: (String) -> Unit, selectedFilter: String) {
    val selected = opcion == selectedFilter
    FilterChip(
        onClick = { onFilterChange(opcion) },
        label = {
            Text(opcion)
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Filtro seleccionado",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else null
    )
}

/**
 * Un composable que muestra una lista de tareas pendientes.
 *
 * @param tareas La lista de tareas a mostrar.
 * @param study2ViewModel El ViewModel que se utiliza para gestionar las tareas.
 * @param navController El NavController que se utiliza para la navegación.
 * @param modulos La lista de módulos a los que pertenecen las tareas.
 */
@Composable
fun MyLazyColumn(
    tareas: List<Tarea>,
    studyViewModel: StudyViewModel,
    navController: NavController,
    modulos: List<Modulo>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tareas) { tarea ->
            // Encuentra el módulo al que pertenece la tarea comparando tarea.moduloId con modulo.id
            val modulo = modulos.find { it.id == tarea.moduloId }

            // Podrías hacer un check si es null (solo por seguridad)
            if (modulo != null) {
                CardTareaPendiente(tarea, modulo, studyViewModel, navController)
            }
        }
    }
}


/**
 * Un composable que representa una tarjeta de tarea pendiente.
 *
 * @param tarea La tarea a mostrar.
 * @param modulo El módulo al que pertenece la tarea.
 * @param study2ViewModel El ViewModel que se utiliza para gestionar las tareas.
 * @param navController El NavController que se utiliza para la navegación.
 * @param modifier El modificador que se aplica a la tarjeta.
 */
@Composable
fun CardTareaPendiente(
    tarea: Tarea,
    modulo: Modulo,
    studyViewModel: StudyViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var expandir by rememberSaveable { mutableStateOf(false) }
    Card(
        shape = shapes.extraSmall,
        modifier = modifier
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(//Aplica modificador de animación
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,//Para que no haya rebote
                        stiffness = Spring.StiffnessLow// para que el resorte sea un poco más rígido
                    )
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                InsertarImagenInternet(modulo.imagen, modifier = Modifier.size(30.dp))
                Column(
                    modifier = Modifier
                        .weight(4f)
                ) {
                    Text(
                        text = modulo.nombre,// Falta implementar
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(start = 10.dp)
                    )
                }
                BotonDesplegar(
                    expanded = expandir,
                    onClick = { expandir = !expandir },
                    modifier = Modifier.weight(0.5f)
                )
            }
            if (expandir) {
                Column {
                    Text(
                        text = "Tarea: ${tarea.nombreTarea}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(start = 10.dp)
                    )
                    Text(
                        text = "Fecha de vencimiento: ${tarea.fechaVencimiento}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(start = 10.dp)
                    )
                    Text(
                        text = studyViewModel.calcularTiempoRestanteConMinutos(tarea.fechaVencimiento),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .padding(start = 10.dp)
                    )
                    if (tarea.detalles!!.isNotEmpty()) {
                        Text(
                            text = "Detalles: ${tarea.detalles}",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            navController.navigate(
                                "ViewActualizarTarea/${modulo.id}/${tarea.id}" // Navega con los IDs necesarios
                            )
                        },
                        modifier = Modifier.width(150.dp)
                    ) {
                        Text(text = "Actualizar")
                    }
                    Button(
                        onClick = {
                            val tareaConModulo =
                                tarea.copy(moduloId = modulo.id) // Asegura que la tarea tenga su módulo asignado
                            studyViewModel.terminarTarea(tareaConModulo)
                            expandir = false
                        },
                        modifier = Modifier
                            .width(150.dp)
                    ) {
                        Text(
                            text = "Terminar tarea"
                        )
                    }
                }
            }
        }
    }
}

/**
 * Un composable que representa un botón para desplegar o contraer la información de una tarea.
 *
 * @param expanded Indica si la información de la tarea está expandida (true) o contraída (false).
 * @param onClick La función lambda que se llama cuando se hace clic en el botón.
 * @param modifier El modificador que se aplica al botón.
 */
@Composable
private fun BotonDesplegar(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(48.dp)
            .semantics {
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = if (expanded) "Contraer tarea" else "Expandir tarea",
                        action = {
                            onClick()
                            true // Indica que la acción se realizó correctamente
                        }
                    )
                )
            }
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = null, // Deja null porque el label ya lo describe
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}


/**
 * Componente que muestra un botón de acción flotante (FAB) con una opción para navegar.
 * @param navController Controlador de navegación para manejar la navegación de la aplicación.
 */
@Composable
fun MyFAB(navController: NavHostController) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    FloatingActionButton(
        onClick = {
            navController.navigate("ViewNuevaTarea")
        },
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {}
                ) { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add"
        )
    }
}

/**
 * Un composable que representa un menú lateral modal.
 *
 * @param navController El NavHostController que se utiliza para la navegación.
 * @param drawerState El estado del drawer.
 * @param contenido El contenido que se muestra en la pantalla principal.
 */
@Composable
fun MyModalDrawer(
    navController: NavHostController,
    drawerState: DrawerState,
    contenido: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val listaOpcionesModalDrawer2 = listOf(
        @Composable { Icon(Icons.Default.AddCircle, "Agregar Módulo") } to "Nuevo Módulo",
        @Composable { Icon(Icons.Default.Warning, "Eliminar Módulo") } to "Eliminar Módulo",
        @Composable { Icon(Icons.Default.Add, "Agregar Tarea") } to "Nueva Tarea",
        @Composable { Icon(Icons.Default.List, "Tareas Pendientes") } to "Tareas Pendientes",
        @Composable { Icon(Icons.Default.Info, "Dashboard") } to "Dashboard",
    )
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,//True para que se cierre al hacer click fuera del menú
        drawerContent = {
            ModalDrawerSheet(
                drawerTonalElevation = 100.dp,
                modifier = Modifier
                    .width(300.dp)
            ) {
                NavBarHeader()//Aquí se llama a la cabecera del menú lateral
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Hola...! ¿Qué deseas hacer?",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(start = 8.dp, bottom = 24.dp, top = 24.dp)
                    )
                    HorizontalDivider(Modifier.padding(horizontal = 8.dp))
                    listaOpcionesModalDrawer2.forEach { item ->
                        NavigationDrawerItem(
                            label = { Text(item.second) },
                            selected = false,
                            icon = item.first,
                            onClick = {
                                coroutineScope.launch {
                                    when (item.second) {
                                        "Nuevo Módulo" -> navController.navigate("ViewNuevoModulo")
                                        "Eliminar Módulo" -> navController.navigate("ViewEliminarModulo")
                                        "Nueva Tarea" -> navController.navigate("ViewNuevaTarea")
                                        "Tareas Pendientes" -> navController.navigate("ViewTareasPendientes")
                                        "Dashboard" -> navController.navigate("ViewDashboard")
                                    }
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }
                        )//NavigationDrawerItem
                        HorizontalDivider(Modifier.padding(horizontal = 8.dp))
                    }
                    Text(
                        text = "Ir a:",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 24.dp, bottom = 24.dp)
                    )
                    InsertarBotonMoodle(
                        context = context,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 24.dp)
                    )
                    InsertarBotonAules(
                        context = context,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 24.dp)
                    )
                    InsertarBotonGoogle(
                        context = context,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 24.dp)
                    )
                }


            }
        }
    ) {
        contenido()
    }
}

/**
 * Un composable que representa la cabecera del menú lateral.
 */
@Composable
fun NavBarHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InsertarImagen(
            imagen = R.drawable.llama_modal,
            contentDescription = "Imagen de llama",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}

/**
 * Un composable que muestra una imagen.
 *
 * @param imagen El recurso de imagen.
 * @param contentDescription La descripción del contenido de la imagen para accesibilidad.
 * @param modifier El modificador que se aplica a la imagen.
 * @param contentScale Cómo se escala la imagen para llenar el espacio asignado.
 */
@Composable
fun InsertarImagen(
    @DrawableRes imagen: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    Image(
        painter = painterResource(imagen),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}

/**
 * Un composable que muestra un icono de notificación animado.
 */
@Composable
fun NotificacionAnimada() {
    val value2 by rememberInfiniteTransition(label = "")
        .animateFloat(
            initialValue = 10f,
            targetValue = -10f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 600,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            ),
            label = "Swing Animation"
        )
    BadgedBox(
        badge = { Badge { Text(text = "1") } },
        modifier = Modifier.padding(10.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.Notifications,
            contentDescription = "Notificaciones pendientes",
            modifier = Modifier
                .clickable { /*Mostrar lista desplegable*/ }
                .semantics {
                    customActions = listOf(
                        CustomAccessibilityAction(
                            label = "Abrir notificaciones",
                            action = {
                                // Mostrar lista desplegable de notificaciones
                                true
                            }
                        )
                    )
                }
                .graphicsLayer(
                    transformOrigin = TransformOrigin(
                        pivotFractionX = 0.5f,
                        pivotFractionY = 0.0f
                    ),
                    rotationZ = value2
                )
        )
    }
}

/**
 * Un composable que representa la barra superior centrada con una imagen.
 *
 * @param drawerState El estado del drawer.
 * @param navController El NavController que se utiliza para la navegación.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBarCenterImage(
    drawerState: DrawerState,//Crear el parámetro de estado Drawer
    navController: NavController,
    //activity: Activity? = null
) {
    //val activity = (LocalContext.current as? Activity)
    // Obtenemos la Activity desde el CompositionLocal
    val activity = LocalActivity.current
    val corutina = rememberCoroutineScope()
    var expandirOpciones by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        // Título de la barra superior
        title = {
            Text(
                text = "Tareas Pendientes",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 24.sp
            )
        },
        // Icono de navegación
        navigationIcon = {
            IconButton(
                onClick = {
                    corutina.launch {//Añadir la corutina para la iniciación de menú lateral cerrado
                        drawerState.apply {
                            if (drawerState.isClosed) open() else close()
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        // Acciones en la barra superior
        actions = {
            NotificacionAnimada()
            Box {
                IconButton(
                    onClick = {
                        expandirOpciones = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Add Items"
                    )
                }
                DropdownMenu(
                    expanded = expandirOpciones, // Controla si el menú está visible
                    onDismissRequest = {
                        expandirOpciones = false
                    }, // Acción para cerrar el menú al hacer clic fuera
                ) {
                    DropdownMenuItem(
                        text = { Text("Cuenta") },
                        onClick = { expandirOpciones = false }, // Acción al seleccionar "Edit"
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.AccountCircle,
                                contentDescription = null
                            )
                        })
                    DropdownMenuItem(
                        text = { Text("Cerrar Sesión") },
                        onClick = {
                            expandirOpciones = false
                            //navController.navigate("ViewInicioSesion")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Clear,
                                contentDescription = null
                            )
                        })
                    DropdownMenuItem(
                        text = { Text("Salir") },
                        onClick = {
                            expandirOpciones = false
                            activity?.finishAffinity()//Cerrar aplicación
                        }, // Acción al seleccionar "Settings"
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.ExitToApp,
                                contentDescription = null
                            )
                        }
                    )
                }
            }

        },
        // Colores personalizados
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

/**
 * Un composable que representa un switch personalizado para cambiar el idioma.
 *
 * @param idioma El estado actual del idioma (true para español, false para inglés).
 * @param cambioIdioma La función lambda que se llama cuando se cambia el idioma.
 * @param modifier El modificador que se aplica al switch.
 */
@Composable
fun InsertarSwitch(
    idioma: Boolean,
    cambioIdioma: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(48.dp),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopEnd)
                .padding(end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .semantics(mergeDescendants = true) {}
            ) {
                Text(
                    text = "ESP"
                )
                Image(
                    painter = painterResource(R.drawable.espania),
                    contentDescription = "Bandera de España",
                    modifier = Modifier.size(30.dp)
                )
            }
            Switch(
                checked = idioma,//Indica si el interruptor está marcado. Este es el estado del elemento Switch componible. Puede ser true o false
                onCheckedChange = cambioIdioma, //Es la devolución de llamada a la que se llamará cuando se haga clic en el interruptor. En este caso un lambda
                colors = SwitchDefaults.colors(
                    //Establesco los mismos colores para el desactivado y activado
                    uncheckedThumbColor = MaterialTheme.colorScheme.outline,//Color icono desactivado
                    checkedThumbColor = MaterialTheme.colorScheme.outline,//Color ícono activado
                    uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,//Color pista desactivada
                    checkedTrackColor = MaterialTheme.colorScheme.secondaryContainer,//Color pista activada
                    uncheckedBorderColor = MaterialTheme.colorScheme.outline,//Color de borde desactivado
                    checkedBorderColor = MaterialTheme.colorScheme.outline//Color de borde activado
                ),
                modifier = modifier
                    .wrapContentWidth(Alignment.End)//lo alinea a la derecha y ajusta su contenedor al tamaño mínimo del contenido
                    .semantics {
                        customActions = listOf(
                            CustomAccessibilityAction(
                                label = if (idioma) "Cambiar a Español" else "Cambiar a Inglés",
                                action = {
                                    cambioIdioma(!idioma)
                                    true // Devuelve true para indicar que la acción se completó
                                }
                            )
                        )
                    }
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .semantics(mergeDescendants = true) {}
            ) {
                Text(
                    text = "ENG"
                )
                Image(
                    painterResource(R.drawable.reinounido),
                    contentDescription = "Bandera del Reino Unido",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

/**
 * Un composable que muestra el logotipo de la aplicación "Study".
 *
 * @param modifier El modificador que se aplica al logotipo.
 */
@Composable
fun Logo(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
    ) {
        InsertarImagenLogo()
        Text(
            text = "tudy",
            fontSize = 100.sp,
            color = Color.Black,
            letterSpacing = 0.em,
            fontWeight = FontWeight.ExtraBold,
            modifier = modifier
        )
    }
}
