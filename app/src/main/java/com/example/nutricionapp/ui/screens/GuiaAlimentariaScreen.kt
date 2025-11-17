package com.example.nutricionapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nutricionapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuiaAlimentariaScreen(
    onBack: () -> Unit
) {
    // Paleta local (misma que Home)
    val GreenPrimary = Color(0xFF7FB972)
    val GreenPrimaryDark = Color(0xFF4F7D49)
    val GreenContainer = Color(0xFFDFF1DA)

    Box(modifier = Modifier.fillMaxSize()) {

        // Fondo con imagen
        Image(
            painter = painterResource(id = R.drawable.bg_guia),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Capa blanca suave
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.6f))
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // Top bar simple
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Guía diaria de alimentación",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Text(
                    text = "Porciones y grupos de alimentos para el día a día, basados en guías alimentarias chilenas para escolares.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
                )

                // Tarjeta resumen
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = GreenContainer.copy(alpha = 0.8f)
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Resumen rápido",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = GreenPrimaryDark
                        )
                        Text(
                            text = "Estas recomendaciones están pensadas para niños en edad escolar, considerando un día típico de alimentación.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                        )
                    }
                }

                // Grupos de alimentos
                GuiaItemCard(
                    emoji = "🥛",
                    titulo = "Lácteos",
                    subtitulo = "3 veces al día",
                    detalle = "Ejemplos: 1 vaso de leche (200 cc), 1 yogurt (135 cc). Aportan calcio y proteína para huesos y dientes fuertes.",
                    chip = "Huesos fuertes",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                GuiaItemCard(
                    emoji = "🥗",
                    titulo = "Verduras",
                    subtitulo = "2–3 veces al día",
                    detalle = "1 taza por vez, idealmente de distintos colores. Ayudan a cuidar el intestino, la vista y la defensa del cuerpo.",
                    chip = "Fibra y vitaminas",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                GuiaItemCard(
                    emoji = "🍊",
                    titulo = "Frutas",
                    subtitulo = "2 veces al día",
                    detalle = "1 unidad por vez (ej. 1 manzana, 1 plátano, 1 naranja). Entregan energía rápida y vitamina C.",
                    chip = "Energía natural",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                GuiaItemCard(
                    emoji = "🥣",
                    titulo = "Legumbres",
                    subtitulo = "2 veces por semana",
                    detalle = "1 taza por vez. Ejemplos: porotos, lentejas, garbanzos. Reemplazan la carne y ayudan a mantener la saciedad.",
                    chip = "Proteína vegetal",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                GuiaItemCard(
                    emoji = "🐟",
                    titulo = "Pescado",
                    subtitulo = "2 veces por semana",
                    detalle = "1 presa chica. Idealmente pescado al horno, al vapor o a la plancha. Aporta grasas saludables para el cerebro y el corazón.",
                    chip = "Grasas buenas",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                GuiaItemCard(
                    emoji = "🥩",
                    titulo = "Huevos y carne",
                    subtitulo = "3 veces por semana",
                    detalle = "1 presa chica o 1 huevo. Prioriza preparaciones a la plancha, al horno o guisadas con pocas grasas.",
                    chip = "Proteína y hierro",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                GuiaItemCard(
                    emoji = "🍞",
                    titulo = "Cereales y papas",
                    subtitulo = "1–2 veces al día",
                    detalle = "3/4 de taza por vez. Prefiere panes integrales, avena, arroz o papas cocidas.",
                    chip = "Energía para el día",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                GuiaItemCard(
                    emoji = "💧",
                    titulo = "Agua",
                    subtitulo = "6–8 vasos al día",
                    detalle = "Ofrecer agua sin azúcar y a libre demanda. Evitar bebidas azucaradas, jugos en caja y energéticas.",
                    chip = "Hidratación",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Estas porciones son una guía: ajusta según el apetito y nivel de actividad de tu hijo, siempre respetando sus señales de hambre y saciedad.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Composable
private fun GuiaItemCard(
    emoji: String,
    titulo: String,
    subtitulo: String,
    detalle: String,
    chip: String,
    greenPrimary: Color,
    greenPrimaryDark: Color,
    greenContainer: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = greenContainer.copy(alpha = 0.85f)
        ),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(14.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(greenPrimary.copy(alpha = 0.18f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = emoji, style = MaterialTheme.typography.titleLarge)
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = subtitulo,
                    style = MaterialTheme.typography.bodySmall,
                    color = greenPrimaryDark
                )
                Text(
                    text = detalle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                )

                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color.White.copy(alpha = 0.4f),
                                    Color.White.copy(alpha = 0.8f)
                                )
                            ),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = chip,
                        style = MaterialTheme.typography.labelSmall,
                        color = greenPrimaryDark
                    )
                }
            }
        }
    }
}
