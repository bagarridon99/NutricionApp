package com.example.nutricionapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nutricionapp.data.NutricionRepository
import com.example.nutricionapp.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Tu estado (se mantiene igual)
data class NutricionUiState(
    val gruposAlimentarios: List<GrupoAlimento> = emptyList(),
    val colaciones: List<Colacion> = emptyList(),
    val consejosActoComer: List<Consejo> = emptyList(),
    val actividadesFisicas: List<ActividadFisica> = emptyList(),
    val consejosSueno: List<Consejo> = emptyList()
)

class NutricionViewModel : ViewModel() {

    // Usamos MutableStateFlow para poder actualizar datos si fuera necesario en el futuro
    private val _uiState = MutableStateFlow(NutricionUiState())
    val uiState: StateFlow<NutricionUiState> = _uiState.asStateFlow()

    init {
        // Cargamos los datos al iniciar el ViewModel
        cargarDatos()
    }

    private fun cargarDatos() {
        _uiState.value = NutricionUiState(
            gruposAlimentarios = NutricionRepository.obtenerGruposAlimentarios(),
            colaciones = NutricionRepository.obtenerColaciones(),
            consejosActoComer = NutricionRepository.obtenerConsejosActoDeComer(),
            actividadesFisicas = NutricionRepository.obtenerActividadesFisicas(),
            consejosSueno = NutricionRepository.obtenerConsejosSueno()
        )
    }
}