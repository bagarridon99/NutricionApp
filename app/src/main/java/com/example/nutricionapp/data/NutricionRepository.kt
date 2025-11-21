package com.example.nutricionapp.data

import com.example.nutricionapp.data.model.*

object NutricionRepository {

    fun obtenerGruposAlimentarios(): List<GrupoAlimento> = listOf(
        GrupoAlimento("Lácteos", "3 veces al día", "Leche 200 ml, yogurt 135 ml", "Los lácteos ayudan a formar huesos y dientes fuertes gracias al calcio."),
        GrupoAlimento("Verduras", "2 a 3 veces al día", "1 taza por vez", "Aportan vitaminas, minerales y fibra. Regulan la digestión."),
        GrupoAlimento("Frutas", "2 veces al día", "1 unidad mediana", "Aportan fibra, agua y vitaminas esenciales."),
        GrupoAlimento("Legumbres", "2 veces por semana", "1 taza", "Ricas en proteínas y fibra, salud del corazón."),
        GrupoAlimento("Pescado", "2 veces por semana", "1 presa chica", "Aporta omega-3 para el cerebro y corazón."),
        GrupoAlimento("Huevos y carne", "3 veces por semana", "1 presa chica/huevo", "Aportan proteínas para crecimiento muscular."),
        GrupoAlimento("Cereales y papas", "1 a 2 veces al día", "3/4 taza", "Principal fuente de energía diaria."),
        GrupoAlimento("Agua", "6 a 8 vasos al día", "Libre demanda", "Evitar bebidas azucaradas; hidrata sin calorías.")
    )

    fun obtenerColaciones(): List<Colacion> = listOf(
        Colacion("Yogurt + fruta", "1 yogurt (100–150 ml) + 1 fruta", "Aporta proteínas, calcio, fibra y vitaminas."),
        Colacion("Palitos de verdura", "Verduras crudas + huevo duro", "Mejora la saciedad y entrega nutrientes."),
        Colacion("Frutos secos + fruta", "1 puñado (sin sal) + 1 fruta", "Grasas saludables + fibra."),
        Colacion("1/2 marraqueta", "Quesillo, tomate, palta", "Incluye verduras y lácteos, bajo en procesados.")
    )

    fun obtenerConsejosActoDeComer(): List<Consejo> = listOf(
        Consejo("Independencia", "Tu hijo decide cuánto comer."),
        Consejo("Ambiente", "Comer sin pantallas."),
        Consejo("Orden", "Comenzar con verduras.")
    )

    // 👇 AQUÍ ESTÁ EL CAMBIO IMPORTANTE PARA QUE COINCIDA CON EL MODELO NUEVO
    fun obtenerActividadesFisicas(): List<ActividadFisica> = listOf(
        ActividadFisica(
            nombre = "Juegos de fuerza",
            ejemplos = listOf("Trepar", "Cuerda", "Barras"),
            beneficio = "Fortalece huesos y músculos."
        ),
        ActividadFisica(
            nombre = "Juegos de resistencia",
            ejemplos = listOf("Fútbol", "Básquetbol", "Patines"),
            beneficio = "Mejora corazón, pulmones y energía."
        )
    )

    fun obtenerConsejosSueno(): List<Consejo> = listOf(
        Consejo("Horas necesarias", "Niños deben dormir entre 9–11 horas."),
        Consejo("Sin pantallas", "Evitar pantallas 1 hora antes de dormir."),
        Consejo("Ambiente ideal", "Oscuro, silencioso y fresco."),
        Consejo("Rutina", "Leer, higiene y dejar ropa lista para mañana.")
    )
}