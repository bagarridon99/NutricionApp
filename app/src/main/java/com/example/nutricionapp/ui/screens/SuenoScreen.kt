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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nutricionapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuenoScreen(
    onBack: () -> Unit
) {
    val GreenPrimary = Color(0xFF7FB972)
    val GreenPrimaryDark = Color(0xFF4F7D49)
    val GreenContainer = Color(0xFFDFF1DA)

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.bg_sueno),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

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
                        text = "Sueño y descanso",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Text(
                    text = "El sueño es clave para el crecimiento, el aprendizaje y el buen ánimo. Los niños en edad escolar idealmente deberían dormir entre 9 y 11 horas por noche.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                )

                SuenoCard(
                    emoji = "🕘",
                    titulo = "Horario recomendado",
                    detalle = "Idealmente dormirse alrededor de las 21:00 para alcanzar entre 9 y 11 horas de sueño, dependiendo de la hora en que debe despertar.",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                SuenoCard(
                    emoji = "📵",
                    titulo = "Antes de dormir",
                    detalle = "Evitar pantallas (celular, tablet, TV) al menos 1 hora antes de dormir. La luz de las pantallas puede dificultar conciliar el sueño.",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                SuenoCard(
                    emoji = "🌙",
                    titulo = "Ambiente ideal",
                    detalle = "La habitación debe estar lo más oscura posible, con poco ruido y temperatura agradable. Evitar juguetes muy estimulantes sobre la cama.",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                SuenoCard(
                    emoji = "☕",
                    titulo = "Bebidas y alimentos estimulantes",
                    detalle = "Evitar café, bebidas azucaradas, energéticas y grandes cantidades de chocolate en la tarde-noche. También es mejor evitar comidas muy pesadas justo antes de dormir.",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                SuenoCard(
                    emoji = "📚",
                    titulo = "Rutina familiar",
                    detalle = "Crear una rutina tranquila antes de dormir: higiene (baño, dientes), dejar la mochila y ropa lista, leer un cuento o conversar brevemente sobre el día.",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Un buen descanso, junto a la actividad física diaria y una alimentación equilibrada, ayuda a que tu hijo tenga mejor ánimo, concentración y rendimiento escolar.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Composable
private fun SuenoCard(
    emoji: String,
    titulo: String,
    detalle: String,
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
                    text = detalle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.95f)
                )
            }
        }
    }
}
