package com.example.nutricionapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.nutricionapp.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Card


// Paleta local basada en tonos “kiwi”
private val GreenPrimary = Color(0xFF7FB972)
private val GreenPrimaryDark = Color(0xFF4F7D49)
private val GreenContainer = Color(0xFFDFF1DA)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    goGuia: () -> Unit,
    goColaciones: () -> Unit,
    goActividad: () -> Unit,
    goSueno: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {

        // 🔹 Fondo completo con imagen
        Image(
            painter = painterResource(id = R.drawable.bg_app),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 🔹 Capa blanca/verdosa suave para que el contenido sea legible
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.55f))
        )

        // 🔹 CONTENIDO de la app encima del fondo
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                // Banner principal (entra a Guía diaria)
                HomeBanner(onPrimaryAction = goGuia)

                // Título de sección
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Explora la app",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Revisa colaciones, actividad física y sueño para complementar la guía diaria.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
                    )
                }

                // Secciones principales (sin guía diaria repetida)
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                    HomeSectionCard(
                        emoji = "🍎",
                        title = "Colaciones saludables",
                        description = "Ideas rápidas, fáciles y económicas para la lonchera.",
                        onClick = goColaciones
                    )

                    HomeSectionCard(
                        emoji = "⚽",
                        title = "Actividad física",
                        description = "Juegos y deportes para cumplir 60 minutos de movimiento al día.",
                        onClick = goActividad
                    )

                    HomeSectionCard(
                        emoji = "😴",
                        title = "Sueño y descanso",
                        description = "Horas recomendadas de sueño y tips para una rutina nocturna tranquila.",
                        onClick = goSueno
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Frase final
                Text(
                    text = "Una alimentación equilibrada, movimiento diario y buen dormir son la base de una infancia saludable.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeBanner(
    onPrimaryAction: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
        ) {

            // Imagen del banner (la de NutriEscolar)
            Box(modifier = Modifier.matchParentSize()) {
                Image(
                    painter = painterResource(id = R.drawable.banner_bg),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // Velado claro para “bajar” la foto
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.White.copy(alpha = 0.55f))
                )
            }

            // Gradiente verde coherente con el fondo
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                GreenPrimaryDark.copy(alpha = 0.55f),
                                GreenContainer.copy(alpha = 0.70f)
                            )
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .matchParentSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Texto + botón
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(end = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "NutriEscolar",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                    Text(
                        text = "Apoya la alimentación, actividad física y descanso de tu hijo con recomendaciones simples y claras.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.9f),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Button(
                        onClick = onPrimaryAction,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GreenPrimary,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Ver guía diaria")
                    }
                }

                // Icono redondo
                Box(
                    modifier = Modifier
                        .weight(1.1f)
                        .height(110.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(85.dp)
                            .clip(CircleShape)
                            .background(
                                Color.White.copy(alpha = 0.96f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "👨‍👩‍👧‍👦",
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeSectionCard(
    emoji: String,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    val tagLabel = when (title) {
        "Colaciones saludables" -> "Alimentación"
        "Actividad física" -> "Movimiento"
        "Sueño y descanso" -> "Descanso"
        else -> ""
    }

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = GreenContainer.copy(alpha = 0.75f) // 💚 Verde translúcido
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(1.dp, GreenPrimary.copy(alpha = 0.22f))
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícono redondo
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(GreenPrimary.copy(alpha = 0.18f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = emoji,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color(0xFF2A2A2A)
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF2A2A2A).copy(alpha = 0.8f)
                )

                if (tagLabel.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.White.copy(alpha = 0.65f),
                                shape = RoundedCornerShape(50)
                            )
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = tagLabel,
                            style = MaterialTheme.typography.labelSmall,
                            color = GreenPrimaryDark
                        )
                    }
                }
            }
        }
    }
}