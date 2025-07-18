package com.example.study.ui.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.study.data.local.ModulosDatabase
import com.example.study.data.local.entity.Modulo
import com.example.study.data.local.entity.Tarea
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class StudyViewModel @Inject constructor(private val database: ModulosDatabase) : ViewModel() {

    // Flujos privados (mutable) y públicos (inmutables)
    private val _modulosFlow = MutableStateFlow<List<Modulo>>(emptyList())
    val modulosFlow: StateFlow<List<Modulo>> = _modulosFlow.asStateFlow()

    private val _tareasFlow = MutableStateFlow<List<Tarea>>(emptyList())
    val tareasFlow: StateFlow<List<Tarea>> = _tareasFlow.asStateFlow()

    // Se cargan los datos al inicializar el ViewModel
    init {
        viewModelScope.launch {
            // Consultas a la BD (suspend)
            val modulosList = database.modulosTareasDao().obtenerTodosModulos()
            val tareasList = database.modulosTareasDao().obtenerTodasTareas()

            // Asignamos las listas a los flows
            _modulosFlow.value = modulosList
            _tareasFlow.value = tareasList
        }
    }

    fun eliminarModulo(modulo: Modulo) {
        viewModelScope.launch {
            database.modulosTareasDao().eliminarModulo(modulo)
            val modulosList = database.modulosTareasDao().obtenerTodosModulos()
            _modulosFlow.value = modulosList
        }
    }

    fun actualizarTarea(tarea: Tarea) {
        viewModelScope.launch {
            database.modulosTareasDao().actualizarTarea(tarea)
            val tareasList = database.modulosTareasDao().obtenerTodasTareas()
            _tareasFlow.value = tareasList
        }
    }

    fun terminarTarea(tarea: Tarea) {
        viewModelScope.launch {
            val tareaCompletada = tarea.copy(completado = true)
            database.modulosTareasDao().actualizarTarea(tareaCompletada)
            cargarTareas()
        }
    }

    fun cargarTareas() {
        viewModelScope.launch {
            val tareasList = database.modulosTareasDao().obtenerTodasTareas()
            _tareasFlow.value = tareasList
        }
    }

    fun cargarModulos() {
        viewModelScope.launch {
            val modulosList = database.modulosTareasDao().obtenerTodosModulos()
            _modulosFlow.value = modulosList
        }
    }

    fun insertarModulo(modulo: Modulo) {
        viewModelScope.launch {
            database.modulosTareasDao().insertarModulo(modulo)
            // Una vez insertado, puedes refrescar la lista si quieres
            val modulosList = database.modulosTareasDao().obtenerTodosModulos()
            _modulosFlow.value = modulosList
        }
    }

    fun insertarTarea(tarea: Tarea) {
        viewModelScope.launch {
            database.modulosTareasDao().insertarTarea(tarea)
            // Una vez insertado, refrescamos también la lista de tareas
            val tareasList = database.modulosTareasDao().obtenerTodasTareas()
            _tareasFlow.value = tareasList
        }
    }


    /**
     * Calcula el tiempo restante para una tarea en formato de días, horas y minutos.
     *
     * @param fechaSeleccionada La fecha de vencimiento de la tarea en formato "yyyy-MM-dd HH:mm".
     * @return Una cadena que representa el tiempo restante para la tarea.
     */
    fun calcularTiempoRestanteConMinutos(fechaSeleccionada: String): String {
        // Configura SimpleDateFormat con la zona horaria local
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC") // Forzar UTC al leer

        // Fecha y hora actual eb UTC
        val fechaActual = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

        // Convierte la fecha seleccionada a un objeto Date UTC
        val fechaSeleccionadaDate = dateFormat.parse(fechaSeleccionada)
            ?: throw IllegalArgumentException("Formato de fecha inválido")
        val fechaSeleccionadaCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
            time = fechaSeleccionadaDate
        }

        // Calcula la diferencia en milisegundos
        val diferenciaEnMilisegundos = fechaSeleccionadaDate.time - fechaActual.time.time

        // Si la fecha ya pasó, retorna "vencida"
        if (diferenciaEnMilisegundos < 0) {
            return "Tarea vencida"
        }

        // Verifica si la fecha de vencimiento es el mismo día que la actual
        val mismoDia =
            fechaActual.get(Calendar.YEAR) == fechaSeleccionadaCalendar.get(Calendar.YEAR) &&
                    fechaActual.get(Calendar.DAY_OF_YEAR) == fechaSeleccionadaCalendar.get(Calendar.DAY_OF_YEAR)

        if (mismoDia) {
            return "Vence hoy"
        }

        // Calcula los días, horas y minutos restantes
        val diferenciaEnSegundos = diferenciaEnMilisegundos / 1000
        val dias = (diferenciaEnSegundos / (24 * 3600)).toInt()
        val horasRestantes = ((diferenciaEnSegundos % (24 * 3600)) / 3600).toInt() - 1
        val minutosRestantes = ((diferenciaEnSegundos % 3600) / 60).toInt()

        // Formatea el mensaje
        return when {
            dias == 0 -> "Faltan $horasRestantes hora${if (horasRestantes != 1) "s" else ""} y $minutosRestantes minuto${if (minutosRestantes != 1) "s" else ""}"
            else -> "Faltan $dias día${if (dias != 1) "s" else ""}"
        }
    }

    /**
     * Muestra un diálogo de selección de fecha y hora. Primero se muestra un DatePickerDialog
     * para seleccionar la fecha, y luego, una vez seleccionada la fecha, se muestra un TimePickerDialog
     * para seleccionar la hora. La fecha y hora seleccionadas se retornan a través del callback
     * `onDateTimeSelected` en formato "yyyy-MM-dd HH:mm".
     *
     * @param context El contexto de la aplicación.
     * @param onDateTimeSelected El callback que se llama cuando se selecciona la fecha y hora.
     *
     * Recibe la fecha y hora seleccionadas como un String en formato "yyyy-MM-dd HH:mm".
     */
    fun seleccionarFechaHora(
        context: Context,
        onDateTimeSelected: (String) -> Unit
    ) {
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                // Actualiza el calendario con la fecha seleccionada
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                // Abre el selector de hora después de seleccionar la fecha
                TimePickerDialog(
                    context,
                    { _, hourOfDay, minute ->
                        // Actualiza el calendario con la hora seleccionada
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)

                        // Formatea la fecha y hora seleccionada
                        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                        val selectedDate = dateFormat.format(calendar.time)

                        // Retorna la fecha seleccionada a través del callback
                        onDateTimeSelected(selectedDate)
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                ).show()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}