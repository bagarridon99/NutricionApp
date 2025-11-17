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
fun ColacionesScreen(
    onBack: () -> Unit
) {
    val GreenPrimary = Color(0xFF7FB972)
    val GreenPrimaryDark = Color(0xFF4F7D49)
    val GreenContainer = Color(0xFFDFF1DA)

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.bg_colaciones),
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
                        text = "Colaciones saludables",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Text(
                    text = "Ideas de colaciones fáciles, rápidas y económicas para la lonchera, junto a explicaciones sencillas para reforzar la educación nutricional.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
                )

                ColacionCard(
                    emoji = "🥣",
                    titulo = "Yogurt + fruta",
                    detalle = "1 yogurt (100–150 cc) + 1 fruta a elección de tu hijo.",
                    porque = "El yogurt aporta proteína y calcio para los huesos; la fruta aporta fibra y vitaminas. Juntos ayudan a mantener la energía y la saciedad.",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                ColacionCard(
                    emoji = "🥕",
                    titulo = "Palitos de verdura + huevo duro / hummus",
                    detalle = "1 vaso de palitos de zanahoria, pepino o apio + 1 huevo duro o 2 cucharadas de hummus.",
                    porque = "Las verduras crujientes ayudan a la saciedad y entregan fibra; el huevo o el hummus aportan proteína para el crecimiento.",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                ColacionCard(
                    emoji = "🥜",
                    titulo = "Frutos secos + fruta",
                    detalle = "1 puñado pequeño de frutos secos sin sal (almendras, maní, nueces) + 1 fruta.",
                    porque = "Los frutos secos entregan grasas saludables para el cerebro; la fruta aporta energía y fibra. Es una colación que mantiene la energía por más tiempo.",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                ColacionCard(
                    emoji = "🥖",
                    titulo = "Media marraqueta con agregado",
                    detalle = "1/2 marraqueta con agregado a elección. Prioriza quesillo, queso fresco, palta, tomate o huevo. Evita embutidos como mortadela, salchichas o jamón laminado.",
                    porque = "El pan entrega energía; si se acompaña con lácteos o verduras, sumas proteína, calcio y vitaminas. Evitar embutidos ayuda a cuidar el corazón y la presión arterial.",
                    greenPrimary = GreenPrimary,
                    greenPrimaryDark = GreenPrimaryDark,
                    greenContainer = GreenContainer
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Tips prácticos:",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = GreenPrimaryDark
                )

                BulletText("Incluye siempre agua como bebida principal, evitando jugos en caja y bebidas azucaradas.")
                BulletText("Prefiere colaciones que combinen fruta o verdura con una fuente de proteína o grasas saludables.")
                BulletText("Deja a tu hijo elegir entre 2–3 opciones saludables para que participe en su propia alimentación.")

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Recuerda: la colación es un complemento, no debe reemplazar el desayuno ni el almuerzo.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Composable
private fun ColacionCard(
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
                Text(
                    text = "¿Por qué es saludable?",
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
