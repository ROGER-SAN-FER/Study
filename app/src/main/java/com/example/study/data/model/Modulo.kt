package com.example.study.data.model

/**
 * Clase de datos que representa un módulo.
 *
 * @property id El identificador único del módulo. Puede ser nulo si el módulo aún no ha sido guardado en la base de datos.
 * @property nombre El nombre del módulo.
 * @property horas La cantidad de horas estimadas para completar el módulo.
 * @property imagen La URL de la imagen asociada al módulo.
 * @property prioridad La prioridad del módulo (un valor numérico, donde un número más alto indica mayor prioridad).
 * @property tareas La lista de tareas asociadas a este módulo. Por defecto, es una lista vacía.
 */
data class Modulo(
    val id: Long?,
    val nombre: String,
    val horas: Int,
    val imagen: String,
    val prioridad: Int,
    val tareas: List<Tarea> = emptyList()
)