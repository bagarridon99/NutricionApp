package com.example.nutricionapp.ui.screens

import androidx.compose.foundation.BorderStroke // 👈 IMPORTANTE
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nutricionapp.ui.viewmodel.ChatViewModel

// Colores
private val UserBubbleColor = Color(0xFF7FB972) // Verde App
private val AiBubbleColor = Color(0xFFF2F4F5)   // Gris muy suave
private val ChatBackground = Color(0xFFFFFFFF)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    onBack: () -> Unit,
    tipoPantalla: String,
    viewModel: ChatViewModel = viewModel()
) {
    // 1. Inicializar chat
    LaunchedEffect(Unit) {
        viewModel.inicializarChat(tipoPantalla)
    }

    val messages by viewModel.messages.collectAsState()

    // 2. Configurar "Personalidad" visual
    val (titulo, emoji) = when (tipoPantalla) {
        "colaciones" -> "Nutricionista" to "🍎"
        "actividad" -> "Entrenador" to "⚽"
        "sueno" -> "Consejero Sueño" to "🌙"
        else -> "Asistente" to "🤖"
    }

    // 3. Sugerencias rápidas según la pantalla (Chips)
    val sugerencias = when(tipoPantalla) {
        "colaciones" -> listOf("Receta con manzana 🍎", "Algo salado 🥕", "¿Qué es una colación?")
        "actividad" -> listOf("Jugué fútbol ⚽", "Salté la cuerda", "¿Cuánta agua tomar? 💧")
        "sueno" -> listOf("Cuento para dormir 📖", "Tengo pesadillas", "Rutina relajante 🌙")
        else -> listOf("Hola", "¿Quién eres?", "Ayuda")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = titulo,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Responde al instante",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = ChatBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 4. LISTA DE MENSAJES
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                reverseLayout = true,
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(messages.reversed()) { msg ->
                    MessageRow(
                        text = msg.text,
                        isUser = msg.isUser,
                        isLoading = msg.isLoading,
                        aiEmoji = emoji
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            // 5. CHIPS DE SUGERENCIAS (ARREGLADO)
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(sugerencias) { texto ->
                    SuggestionChip(
                        onClick = { viewModel.sendMessage(texto) },
                        label = { Text(texto) },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = Color(0xFFF1F8E9),
                            labelColor = UserBubbleColor
                        ),
                        // 👇 AQUÍ ESTABA EL ERROR: Usamos BorderStroke directo, compatible con todo
                        border = BorderStroke(1.dp, UserBubbleColor.copy(alpha = 0.5f))
                    )
                }
            }

            // 6. INPUT MODERNO
            ChatInputModerno(
                onSend = { viewModel.sendMessage(it) }
            )
        }
    }
}

@Composable
fun MessageRow(
    text: String,
    isUser: Boolean,
    isLoading: Boolean,
    aiEmoji: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        // Avatar IA
        if (!isUser) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFFE0E0E0), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = aiEmoji, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        // Burbuja
        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomStart = if (isUser) 20.dp else 4.dp,
                        bottomEnd = if (isUser) 4.dp else 20.dp
                    )
                )
                .background(if (isUser) UserBubbleColor else AiBubbleColor)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            if (isLoading) {
                Text(text = "Escribiendo...", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            } else {
                Text(
                    text = parseBoldText(text),
                    color = if (isUser) Color.White else Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun ChatInputModerno(onSend: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Surface(
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Escribe aquí...") },
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color(0xFFF2F4F5),
                    unfocusedContainerColor = Color(0xFFF2F4F5)
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.width(8.dp))

            val isEnabled = text.isNotBlank()
            IconButton(
                onClick = {
                    if (isEnabled) {
                        onSend(text)
                        text = ""
                    }
                },
                modifier = Modifier
                    .size(52.dp)
                    .background(
                        color = if (isEnabled) UserBubbleColor else Color.Gray.copy(alpha = 0.3f),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Enviar",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun parseBoldText(text: String): androidx.compose.ui.text.AnnotatedString {
    return buildAnnotatedString {
        val parts = text.split("**")
        parts.forEachIndexed { index, part ->
            if (index % 2 == 1) {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(part)
                }
            } else {
                append(part)
            }
        }
    }
}