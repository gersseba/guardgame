package com.gersseba.guardgame.models

import com.gersseba.guardgame.models.common.Chat
import com.gersseba.guardgame.models.common.Chattable
import com.gersseba.guardgame.models.common.Interactable

class Guard(x: Double, y: Double, var tellsTruth: Boolean, override val name: String,
            override val chat: Chat = Chat()
) : BaseModel(x,y), Interactable, Chattable {

    override fun getSystemPrompt(): String {
        return "You are a guard in a game. You can either tell the truth or lie, depending on your 'tellsTruth' property. Your 'tellsTruth' property is $tellsTruth . When asked a question, you will respond with either the truth or a lie based on this property."
    }

    override fun interact(player: Player): String {
        return ""
    }
}
