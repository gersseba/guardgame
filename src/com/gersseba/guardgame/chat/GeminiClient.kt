package com.gersseba.guardgame.chat
import com.google.genai.Chat
import com.google.genai.Client
import com.google.genai.types.GenerateContentConfig
import com.google.genai.types.Content
import com.google.genai.types.Part
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import korlibs.io.file.std.*
import korlibs.io.util.unquote
import korlibs.io.util.unquoted
import kotlinx.serialization.json.*

class GeminiClient {
    private lateinit var client: Client
    private lateinit var model: String

    suspend fun initConfig() = withContext(Dispatchers.IO) {
        val configText = resourcesVfs["api_config.json"].readString()
        val config = Json.parseToJsonElement(configText).jsonObject

        val apiKey = config["apiKey"]?.jsonPrimitive?.content
            ?: throw Exception("API Key missing in config.json")

        // The Java SDK Builder
        client = Client.builder()
            .apiKey(apiKey)
            .build()
        model = config["model"]?.jsonPrimitive?.contentOrNull?.unquote()
            ?: throw Exception("Model missing in config.json")
    }

    /**
     * Starts a new stateful chat session.
     * You should call this once when the player starts talking to an NPC.
     */
    fun startChat(systemPrompt: String): Chat {
        val config = GenerateContentConfig.builder()
            .systemInstruction(Content.fromParts(Part.fromText(systemPrompt)))
            .temperature(0.7f)
            .maxOutputTokens(200)
            .build()

        // Creates a chat session associated with the model and config
        val chat = client.chats.create(model, config)
        return chat
    }

    /**
     * Sends a message within an existing chat session.
     * The history is handled automatically by the 'chat' object.
     */
    suspend fun sendMessage(chat: Chat, userPrompt: String): String = withContext(Dispatchers.IO) {
        try {
            val response = chat.sendMessage(userPrompt)
            response.text() ?: "The guard stares at you blankly..."
        } catch (e: Exception) {
            "Internal Error: ${e.localizedMessage}"
        }
    }

    suspend fun sendMessage(chat: Chat, content: List<Content>): String = withContext(Dispatchers.IO) {
        try {
            val response = chat.sendMessage(content)
            response.text() ?: "The guard stares at you blankly..."
        } catch (e: Exception) {
            "Internal Error: ${e.localizedMessage}"
        }
    }

    fun mapRole(sender: MessageSender): String {
        return when (sender) {
            MessageSender.PLAYER -> "user"
            MessageSender.NPC -> "model"
        }
    }

}