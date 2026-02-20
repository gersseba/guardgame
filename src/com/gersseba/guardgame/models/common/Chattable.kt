package com.gersseba.guardgame.models.common

interface Chattable {

    val chat: Chat

    fun getSystemPrompt(): String
}