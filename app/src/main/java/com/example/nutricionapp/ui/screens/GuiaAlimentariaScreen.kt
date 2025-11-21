package com.example.nutricionapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutricionapp.data.NutricionRepository
import com.example.nutricionapp.data.model.GrupoAlimento

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuiaAlimentariaScreen(onBack: () -> Unit) {
    // Paleta de colores coherente
    val BgColor = Color(0xFFF8F9FA) // Fondo claro
    val GreenGradient = listOf(Color(0xFF7FB972), Color(0xFF4F7D49))
    val IconBgColor = Color(0xFFE8F5E9) // Verde muy claro para fondo de iconos

    // Obtener datos del repositorio
    val gruposAlimentarios = NutricionRepository.obtenerGruposAlimentarios()

    Scaffold(
        containerColor = BgColor,
        topBar = {
            // Cabecera curva con degradado
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                    .background(Brush.verticalGradient(GreenGradient))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    IconButton(
                        onClick = onBack,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.White.copy(alpha = 0.2f),
                            contentColor = Color.White
                        )
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Guía Alimentaria",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 28.sp
                        )
                    )
                    Text(
                        text = "Porciones recomendadas para el día",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(gruposAlimentarios) { grupo ->
                GuiaItemCardModern(grupo, IconBgColor)
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Estas porciones son una guía general. Ajusta según el apetito y actividad de tu hijo.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun GuiaItemCardModern(
    grupo: GrupoAlimento,
    iconBgColor: Color
) {
    val emoji = when (grupo.nombre) {
        "Lácteos" -> "🥛"
        "Verduras" -> "🥗"
        "Frutas" -> "🍊"
        "Legumbres" -> "🥣"
        "Pescado" -> "🐟"
        "Huevos y carne" -> "🥩"
        "Cereales y papas" -> "🍞"
        "Agua" -> "💧"
        else -> "🍽️"
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Ícono con fondo de color
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(iconBgColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = emoji, fontSize = 24.sp)
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = grupo.nombre,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = grupo.frecuencia,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray, fontWeight = FontWeight.Medium)
                )
                Text(
                    text = grupo.porcion,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = grupo.beneficio,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                    maxLines = 2,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
            }
        }
    }
}