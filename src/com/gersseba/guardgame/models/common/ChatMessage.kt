package com.gersseba.guardgame.models.common

import com.gersseba.guardgame.chat.MessageSender

data class ChatMessage (
    val role: MessageSender,
    val text: String
)
