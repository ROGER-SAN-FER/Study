package com.example.study.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "tarea",
    foreignKeys = [ForeignKey(
        entity = Modulo::class,
        parentColumns = ["id"],
        childColumns = ["moduloId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["moduloId"])]
)
data class Tarea (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "nombreTarea")val nombreTarea: String,
    @ColumnInfo(name = "fechaVencimiento")val fechaVencimiento: String,
    @ColumnInfo(name = "detalles")val detalles: String?,
    @ColumnInfo(name = "completado")val completado: Boolean,
    @ColumnInfo(name = "moduloId") val moduloId: Long
)