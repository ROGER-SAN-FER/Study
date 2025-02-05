package com.example.study.data.model

/**
 * Clase de datos que representa una tarea.
 *
 * @property id El identificador único de la tarea. Puede ser nulo si la tarea aún no ha sido guardada en la base de datos.
 * @property nombreTarea El nombre de la tarea.
 * @property fechaVencimiento La fecha de vencimiento de la tarea, en formato "yyyy-MM-dd HH:mm".
 * @property detalles Detalles adicionales sobre la tarea (opcional).
 * @property completado Indica si la tarea ha sido completada.
 * @property modulo El módulo al que pertenece esta tarea.
 */
data class Tarea(
    val id: Long?,
    val nombreTarea: String,
    val fechaVencimiento: String,
    val detalles: String?,
    val completado: Boolean,
    val modulo: Modulo
)