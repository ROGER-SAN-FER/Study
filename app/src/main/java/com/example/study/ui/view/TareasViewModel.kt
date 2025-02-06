package com.example.study.ui.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class TareasViewModel: ViewModel() {


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