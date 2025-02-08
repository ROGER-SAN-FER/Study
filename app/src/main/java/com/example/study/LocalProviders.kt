package com.example.study

import android.app.Activity
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * CompositionLocal para proveer la Activity de forma segura.
 */
val LocalActivity = staticCompositionLocalOf<Activity?> {
    null // Valor por defecto si nadie la provee
}
