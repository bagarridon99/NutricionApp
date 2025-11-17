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

// --- 🔽 AQUÍ ESTÁ LA CORRECCIÓN 🔽 ---
// Movimos los colores aquí para que sean visibles por todas las funciones del archivo
private val GreenPrimary = Color(0xFF7FB972)
private val GreenPrimaryDark = Color(0xFF4F7D49)
private val GreenContainer = Color(0xFFDFF1DA)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActividadFisicaScreen(
    onBack: () -> Unit
) {
    // Los colores ya no se definen aquí, usan los de arriba

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.bg_actividad),
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

                // Top bar
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
                        text = "Actividad física",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Text(
                    text = "Los niños deben realizar al menos 60 minutos de actividad física moderada al día. Al menos 3 de esos días, la actividad debería ser vigorosa.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                )

                // Card beneficios
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = GreenContainer.copy(alpha = 0.85f)
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Beneficios del movimiento diario",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = GreenPrimaryDark
                        )
                        Text(
                            text = "• Mejora el ánimo, la concentración y el rendimiento escolar.\n" +
                                    "• Fortalece huesos y músculos.\n" +
                                    "• Protege el corazón y los pulmones.\n" +
                                    "• Ayuda a mantener un peso saludable.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.95f)
                        )
                    }
                }

                // Tarjetas de tipos de actividad
                ActividadCard(
                    emoji = "🏃‍♀️",
                    titulo = "Juegos de resistencia",
                    detalle = "Fútbol, básquetbol, vóleibol, correr, andar en bicicleta, skate o patines.",
                    porque = "Ayudan a que el corazón y los pulmones sean más fuertes y a que tu hijo tenga más energía para el día a día.",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                ActividadCard(
                    emoji = "🧗‍♂️",
                    titulo = "Juegos de fuerza",
                    detalle = "Trepar, colgarse de las barras, escalar, empujar o tirar cuerdas, juegos de arrastre.",
                    porque = "Favorecen el desarrollo de músculos y huesos fuertes, fundamentales en la etapa de crecimiento.",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                ActividadCard(
                    emoji = "🤸‍♂️",
                    titulo = "Juegos de equilibrio y coordinación",
                    detalle = "Saltar en un pie, la cuerda, circuitos con conos, caminar por líneas marcadas en el piso.",
                    porque = "Mejoran la coordinación, el equilibrio y la seguridad al moverse.",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                Text(
                    text = "Ideas para familias:",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = GreenPrimaryDark
                )

                BulletText("Salir a caminar en familia después de la comida principal, aunque sean 20–30 minutos.")
                BulletText("Promover juegos activos los fines de semana: plaza, bicicleta, pelota, rayuela, etc.")
                BulletText("Incentivar la participación en talleres deportivos del colegio o la comunidad.")
                BulletText("Asignar pequeñas tareas domésticas que impliquen movimiento (ordenar juguetes, ayudar a regar, barrer espacios pequeños).")

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Recuerda: la actividad física, junto a una alimentación equilibrada y un buen descanso, es la base de una vida saludable y activa.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Composable
private fun ActividadCard(
    emoji: String,
    titulo: String,
    detalle: String,
    porque: String,
    greenPrimary: Color,
    greenPrimaryDark: Color,
    greenContainer: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = GreenContainer.copy(alpha = 0.85f)
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
                Text(
                    text = "¿Por qué es importante?",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = greenPrimaryDark
                )
                Text(
                    text = porque,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                )
            }
        }
    }
}

// Esta es la función que daba error. Ahora puede ver "GreenContainer"
// O podemos usar la versión de "ColacionesScreen" que es más segura.
// Dejemos la versión segura que no depende de los colores locales.
@Composable
private fun BulletText(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text("•", style = MaterialTheme.typography.bodySmall)
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
        )
    }
}