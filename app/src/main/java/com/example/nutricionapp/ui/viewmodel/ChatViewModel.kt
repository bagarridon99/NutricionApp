package com.example.nutricionapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutricionapp.BuildConfig
import com.example.nutricionapp.data.model.ChatMessage
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val generativeModel = GenerativeModel(
        // Usamos el modelo actualizado que sí funciona
        modelName = "gemini-2.5-flash",
        apiKey = BuildConfig.API_KEY,
        generationConfig = generationConfig {
            temperature = 0.7f
        }
    )

    private lateinit var chatSession: com.google.ai.client.generativeai.Chat

    fun inicializarChat(tipoPantalla: String) {
        // Si ya hay mensajes (por ejemplo al rotar pantalla), no reiniciamos nada
        if (_messages.value.isNotEmpty()) return

        // 1. Definimos la personalidad (Instrucción oculta)
        val instruccion = when (tipoPantalla) {
            "colaciones" -> "Eres un Nutricionista experto en niños. El usuario te dirá ingredientes y tú sugerirás una receta saludable, fácil y rápida para la lonchera escolar. Calcula aprox las calorías. Sé amable y breve."
            "actividad" -> "Eres un Entrenador Personal para niños. El usuario te dirá qué ejercicio hizo. Tú calcula las calorías quemadas aproximadas y dales un consejo divertido de hidratación."
            "sueno" -> "Eres un especialista en Sueño Infantil. Da consejos relajantes, rutinas para dormir y responde dudas sobre pesadillas de forma calmada y dulce."
            else -> "Eres un asistente útil de salud."
        }

        // 2. Definimos el SALUDO INICIAL (Visible al usuario inmediatamente)
        val mensajeBienvenida = when (tipoPantalla) {
            "colaciones" -> "¡Hola! 🍎 Soy tu Asistente de Nutrición. Dime qué ingredientes tienes en casa y crearé una receta rica y sana para ti."
            "actividad" -> "¡Hola campeón/a! ⚽ Soy tu Entrenador. Cuéntame qué deporte hiciste hoy (y por cuánto tiempo) para ver cuánto te esforzaste."
            "sueno" -> "Hola... 🌙 Soy tu consejero de Sueño. Estoy aquí para ayudarte a tener dulces sueños. ¿Te cuesta dormir o quieres un consejo relajante?"
            else -> "¡Hola! Soy tu asistente virtual. ¿En qué puedo ayudarte hoy?"
        }

        // 3. Iniciamos el chat con la instrucción oculta
        chatSession = generativeModel.startChat(
            history = listOf(
                content(role = "user") { text(instruccion) },
                content(role = "model") { text("Entendido. Asumiré ese rol.") }
            )
        )

        // 4. Mostramos el mensaje de bienvenida INSTANTÁNEAMENTE (sin cargar)
        _messages.value = listOf(
            ChatMessage(
                text = mensajeBienvenida,
                isUser = false // Lo marca como enviado por la IA (gris)
            )
        )
    }

    fun sendMessage(userMessage: String) {
        // Agregamos mensaje usuario
        val userMsg = ChatMessage(text = userMessage, isUser = true)
        _messages.value = _messages.value + userMsg

        // Agregamos "Pensando..."
        val loadingMsg = ChatMessage(isLoading = true, isUser = false)
        _messages.value = _messages.value + loadingMsg

        viewModelScope.launch {
            try {
                val response = chatSession.sendMessage(userMessage)

                // Quitamos carga y ponemos respuesta real
                _messages.value = _messages.value.filter { !it.isLoading } +
                        ChatMessage(text = response.text ?: "No entendí eso...", isUser = false)

            } catch (e: Exception) {
                _messages.value = _messages.value.filter { !it.isLoading } +
                        ChatMessage(text = "Error: ${e.localizedMessage}", isUser = false)
            }
        }
    }
}