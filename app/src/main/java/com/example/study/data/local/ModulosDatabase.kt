package com.example.study.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.study.data.local.dao.ModulosTareasDao
import com.example.study.data.local.entity.Modulo
import com.example.study.data.local.entity.Tarea

// 3. Base de datos Room
@Database(entities = [Modulo::class, Tarea::class], version = 1, exportSchema =
false)
abstract class ModulosDatabase : RoomDatabase() {
    abstract fun modulosTareasDao(): ModulosTareasDao

    companion object {
        @Volatile
        private var INSTANCE: ModulosDatabase? = null
        fun getDatabase(context: Context): ModulosDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ModulosDatabase::class.java,
                    "modulos_bd"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}