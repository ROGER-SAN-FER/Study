package com.example.study.di


import android.content.Context
import androidx.room.Room
import com.example.study.data.local.ModulosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuloDatabase {

    @Provides
    @Singleton
    fun provideModuloDatabase(
        @ApplicationContext context: Context
    ): ModulosDatabase {
        return Room.databaseBuilder(
            context,
            ModulosDatabase::class.java,
            "modulos_bd"
        ).build()
    }
}
