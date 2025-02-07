package com.example.study.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "modulo")
data class Modulo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "nombre")val nombre: String,
    @ColumnInfo(name = "horas")val horas: Int,
    @ColumnInfo(name = "imagen")val imagen: String,
    @ColumnInfo(name = "prioridad")val prioridad: Int,
)