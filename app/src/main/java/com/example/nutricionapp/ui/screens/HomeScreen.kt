package com.example.nutricionapp.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star // Usamos Estrella que es seguro
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    goGuia: () -> Unit,
    goColaciones: () -> Unit,
    goActividad: () -> Unit,
    goSueno: () -> Unit
) {
    // 👇 CAMBIO CLAVE: Fondo más oscurito para que resalten las tarjetas
    val BgColor = Color(0xFFE8EBE9)

    Scaffold(containerColor = BgColor) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            HomeHeader()

            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Meta Diaria 💧",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                WaterTrackerWidget()

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Aprende 🧠",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                BannerGuia(onClick = goGuia)

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Herramientas 🛠️",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Box(modifier = Modifier.weight(1f)) {
                        HomeSquareCard("🍎", "Colaciones", "Ideas ricas", Color(0xFFE8F5E9), goColaciones)
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        HomeSquareCard("⚽", "Actividad", "Juegos", Color(0xFFFFF3E0), goActividad)
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                HomeWideCard("🌙", "Sueño y Descanso", "Rutinas de noche", Color(0xFFE3F2FD), goSueno)
            }
        }
    }
}

@Composable
fun BannerGuia(onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFE8F5E9), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFF2E7D32),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Guía de Alimentación",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
                Text(
                    text = "Revisa las porciones",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}

@Composable
fun WaterTrackerWidget() {
    var vasos by remember { mutableIntStateOf(0) }
    val meta = 8
    val progreso by animateFloatAsState(
        targetValue = if (meta > 0) vasos / meta.toFloat() else 0f,
        label = "ProgresoAgua"
    )

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Hidratación", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = "$vasos de $meta vasos hoy", color = Color.Gray, fontSize = 14.sp)
                }
                IconButton(
                    onClick = { if (vasos > 0) vasos-- },
                    modifier = Modifier.size(32.dp).background(Color(0xFFF5F5F5), CircleShape)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = "Menos", tint = Color.Gray, modifier = Modifier.size(16.dp))
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = { if (vasos < meta) vasos++ },
                    modifier = Modifier.size(40.dp).background(Color(0xFFE3F2FD), CircleShape)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Más", tint = Color(0xFF1976D2))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressIndicator(
                progress = { progreso },
                modifier = Modifier.fillMaxWidth().height(12.dp).clip(RoundedCornerShape(50)),
                color = Color(0xFF2196F3),
                trackColor = Color(0xFFE3F2FD),
            )
            if (vasos >= meta) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "¡Meta cumplida! 🎉", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun HomeHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
            .background(Brush.verticalGradient(listOf(Color(0xFF7FB972), Color(0xFF4F7D49))))
    ) {
        Box(modifier = Modifier.offset(x = 220.dp, y = (-40).dp).size(200.dp).background(Color.White.copy(alpha=0.1f), CircleShape))
        Column(modifier = Modifier.padding(24.dp).align(Alignment.CenterStart)) {
            Text(text = "¡Hola Familia! 👋", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "¿Listos para un día saludable?", color = Color.White.copy(alpha = 0.9f), fontSize = 16.sp)
        }
    }
}

@Composable
fun HomeSquareCard(emoji: String, title: String, subtitle: String, color: Color, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth().height(140.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Box(modifier = Modifier.size(40.dp).background(color, RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center) {
                Text(text = emoji, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = subtitle, color = Color.Gray, fontSize = 12.sp)
        }
    }
}

@Composable
fun HomeWideCard(emoji: String, title: String, subtitle: String, color: Color, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(48.dp).background(color, RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
                Text(text = emoji, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = subtitle, color = Color.Gray, fontSize = 14.sp)
            }
            Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.LightGray)
        }
    }
}