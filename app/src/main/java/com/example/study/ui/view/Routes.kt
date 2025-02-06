package com.example.study.ui.view

sealed class Routes(val route: String) {
    object ViewPrincipal : Routes("ViewPrincipal")
    object ViewInicioSesion : Routes("ViewInicioSesion")
    object ViewRegistro : Routes("ViewRegistro")
    object ViewTareasPendientes : Routes("ViewTareasPendientes")
    object ViewNuevoModulo : Routes("ViewNuevoModulo")
    object ViewNuevaTarea : Routes("ViewNuevaTarea")
    object ViewDashboard : Routes("ViewDashboard")
    object ViewEliminarModulo : Routes("ViewEliminarModulo")
}
