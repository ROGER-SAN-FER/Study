package com.example.study.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ModuloWithTareas(
    @Embedded val modulo: Modulo,
    @Relation(
        parentColumn = "id",
        entityColumn = "moduloId"
    )
    val tareas: List<Tarea>
)