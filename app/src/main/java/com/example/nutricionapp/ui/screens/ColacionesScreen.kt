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
import com.example.nutricionapp.data.model.Colacion
import com.example.nutricionapp.ui.components.ChatFab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColacionesScreen(
    onBack: () -> Unit,
    onChat: () -> Unit
) {
    // 👇 Fondo corregido
    val BgColor = Color(0xFFE8EBE9)
    val GreenGradient = listOf(Color(0xFF7FB972), Color(0xFF4F7D49))
    val IconBgColor = Color(0xFFFFF3E0)

    val colaciones = NutricionRepository.obtenerColaciones()

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
                        text = "Colaciones Saludables",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 28.sp
                        )
                    )
                    Text(
                        text = "Ideas fáciles y nutritivas para la lonchera",
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
            items(colaciones) { colacion ->
                ColacionCardModern(colacion, IconBgColor)
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Recuerda: la colación es un complemento, no reemplaza comidas principales.",
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
private fun ColacionCardModern(
    colacion: Colacion,
    iconBgColor: Color
) {
    val emoji = when {
        colacion.nombre.contains("Yogurt") -> "🥣"
        colacion.nombre.contains("verdura") -> "🥕"
        colacion.nombre.contains("Frutos secos") -> "🥜"
        colacion.nombre.contains("marraqueta") -> "🥖"
        else -> "🍎"
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
                    text = colacion.nombre,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = colacion.detalle,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = colacion.beneficio,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }
        }
    }
}