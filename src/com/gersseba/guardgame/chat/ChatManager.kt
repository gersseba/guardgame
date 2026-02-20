package com.gersseba.guardgame.chat

import com.gersseba.guardgame.models.common.ChatMessage
import com.gersseba.guardgame.models.common.Chattable
import com.gersseba.guardgame.ui.ChatBox
import com.google.genai.Chat
import com.google.genai.types.Content
import com.google.genai.types.Part
import kotlinx.coroutines.launch

class ChatManager(
    private val ui: ChatBox,
    private val api: GeminiClient
) {
    private var currentNpc: Chattable? = null
    private var currentSession: Chat? = null


    /**
     * Call this when the player interacts with an NPC (e.g., clicks on them)
     */
    fun startConversation(npc: Chattable, name: String) {
        this.currentNpc = npc

        initialize(npc, name)
        currentSession = api.startChat(npc.getSystemPrompt())
    }

    private fun initialize(npc: Chattable, name: String) {
        ui.clear()
        ui.setTitle("Talking to ${name}")
        ui.visible = true
        npc.chat.messages.forEach { message ->
            ui.addMessage(message)
        }
    }

    /**
     * Called when the player sends a new message
     */
    fun sendUserMessage(playerMessage: String) {
        println("Player: $playerMessage")
        val npc = currentNpc ?: return
        val session = currentSession ?: return

        ui.stage?.launch {
            val chatMessage = ChatMessage(MessageSender.PLAYER, playerMessage)
            ui.addMessage(chatMessage)
            npc.chat.messages.add(chatMessage)

            println("Sending to API...")
            val contents = npc.chat.messages.map { chatMessage ->
                Content.builder().parts(Part.fromText(chatMessage.text)).role(api.mapRole(chatMessage.role)).build()
            }
            val responseText = api.sendMessage(session, contents)

            val responseMessage = ChatMessage(MessageSender.NPC, responseText)
            println("Received from API: $responseText")

            ui.addMessage(responseMessage)
            npc.chat.messages.add(responseMessage)

        }
    }
}