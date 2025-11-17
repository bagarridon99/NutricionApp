package com.example.nutricionapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nutricionapp.data.NutricionRepository
import com.example.nutricionapp.data.model.*

data class NutricionUiState(
    val gruposAlimentarios: List<GrupoAlimento> = emptyList(),
    val colaciones: List<Colacion> = emptyList(),
    val consejosActoComer: List<Consejo> = emptyList(),
    val actividadesFisicas: List<ActividadFisica> = emptyList(),
    val consejosSueno: List<Consejo> = emptyList()
)

class NutricionViewModel : ViewModel() {

    val uiState = NutricionUiState(
        gruposAlimentarios = NutricionRepository.obtenerGruposAlimentarios(),
        colaciones = NutricionRepository.obtenerColaciones(),
        consejosActoComer = NutricionRepository.obtenerConsejosActoDeComer(),
        actividadesFisicas = NutricionRepository.obtenerActividadesFisicas(),
        consejosSueno = NutricionRepository.obtenerConsejosSueno()
    )
}
