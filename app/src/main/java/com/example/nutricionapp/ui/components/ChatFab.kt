package com.example.nutricionapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face // Icono de carita
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChatFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        containerColor = Color(0xFF7FB972), // Verde App
        contentColor = Color.White,
        modifier = Modifier
            .padding(16.dp)
            .size(64.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Face,
            contentDescription = "Consultar IA",
            modifier = Modifier.size(32.dp)
        )
    }
}