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
import com.example.nutricionapp.data.model.Consejo
import com.example.nutricionapp.ui.components.ChatFab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuenoScreen(
    onBack: () -> Unit,
    onChat: () -> Unit
) {
    // 👇 Fondo corregido
    val BgColor = Color(0xFFE8EBE9)
    val GreenGradient = listOf(Color(0xFF7FB972), Color(0xFF4F7D49))
    val IconBgColor = Color(0xFFF3E5F5)

    val consejosSueno = NutricionRepository.obtenerConsejosSueno()

    Scaffold(
        containerColor = BgColor,
        floatingActionButton = { ChatFab(onClick = onChat) },
        topBar = {
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
                        text = "Sueño y Descanso",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 28.sp
                        )
                    )
                    Text(
                        text = "Clave para el crecimiento y el aprendizaje",
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
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)), // Azul muy claro
                    elevation = CardDefaults.cardElevation(0.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(text = "🕘", fontSize = 32.sp)
                        Column {
                            Text(
                                text = "Horas recomendadas",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = Color(0xFF1565C0))
                            )
                            Text(
                                text = "Entre 9 y 11 horas por noche para escolares.",
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF0D47A1))
                            )
                        }
                    }
                }
            }

            items(consejosSueno) { consejo ->
                SuenoCardModern(consejo, IconBgColor)
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Una rutina constante antes de dormir ayuda a conciliar el sueño.",
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
private fun SuenoCardModern(
    consejo: Consejo,
    iconBgColor: Color
) {
    val emoji = when {
        consejo.titulo.contains("pantallas") -> "📵"
        consejo.titulo.contains("Ambiente") -> "🌙"
        consejo.titulo.contains("estimulantes") -> "☕"
        consejo.titulo.contains("Rutina") -> "📚"
        else -> "💡"
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
                    text = consejo.titulo,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = consejo.descripcion,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
            }
        }
    }
}