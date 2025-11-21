package com.example.nutricionapp.data.model

import java.util.UUID

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val text: String = "",
    val isUser: Boolean = true, // true = Usuario, false = IA (Gemini)
    val isLoading: Boolean = false // Para mostrar los puntitos (...) mientras piensa
)