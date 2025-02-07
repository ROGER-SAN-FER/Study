package com.example.study.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.study.data.local.entity.Modulo
import com.example.study.data.local.entity.Tarea

// 2. DAO (Interfaz para interactuar con la base de datos)
@Dao
interface ModulosTareasDao {

    //Módulos
    @Query("SELECT * FROM modulo")
    suspend fun obtenerTodosModulos(): List<Modulo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarModulo(modulo: Modulo)

    @Delete
    suspend fun eliminarModulo(modulo: Modulo)

    //Tareas
    @Query("SELECT * FROM tarea where completado = false")
    suspend fun obtenerTodasTareas(): List<Tarea>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTarea(tarea: Tarea)

    @Update
    suspend fun actualizarTarea(tarea: Tarea)

    @Delete
    suspend fun eliminarTarea(tarea: Tarea)
}