package com.example.nutricionapp.data.model

data class ActividadFisica(
    val nombre: String,        // Antes era 'titulo'
    val ejemplos: List<String>,
    val beneficio: String
)