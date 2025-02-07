package com.example.study.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tarea")
data class Tarea (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "nombreTarea")val nombreTarea: String,
    @ColumnInfo(name = "fechaVencimiento")val fechaVencimiento: String,
    @ColumnInfo(name = "detalles")val detalles: String?,
    @ColumnInfo(name = "completado")val completado: Boolean,
    @ColumnInfo(name = "modulo")val modulo: Modulo
)