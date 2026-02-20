package com.gersseba.guardgame.ui

import com.gersseba.guardgame.chat.ChatManager
import com.gersseba.guardgame.chat.MessageSender
import com.gersseba.guardgame.models.common.ChatMessage
import korlibs.korge.annotations.KorgeExperimental
import korlibs.korge.ui.*
import korlibs.korge.view.*
import korlibs.image.color.*
import korlibs.math.geom.Size
import kotlinx.coroutines.launch

@OptIn(KorgeExperimental::class)
class ChatBox() : Container() {
    private val chatHistory = uiVerticalStack(width = 400.0)
    private lateinit var scrollable: UIScrollable
    private lateinit var background: View
    private lateinit var title: UIText
    private var inputField: UITextInput? = null

    lateinit var chatManager: ChatManager

    var npcName: String = "Guard"

    init {
        // Listen to window size changes
        var lastWidth = stage?.width ?: 500.0
        var lastHeight = stage?.height ?: 500.0

        onStageResized()

        // Monitor size changes
        addUpdater {
            val currentWidth = stage?.width ?: 500.0
            val currentHeight = stage?.height ?: 500.0

            if (currentWidth != lastWidth || currentHeight != lastHeight) {
                lastWidth = currentWidth
                lastHeight = currentHeight
                onStageResized()
            }
        }
    }

    private fun onStageResized() {
        removeChildren()

        val stageWidth = stage?.width ?: 500.0
        val stageHeight = stage?.height ?: 500.0

        // Calculate dimensions based on percentages
        val margin = stageWidth * 0.05  // 5% margin
        val chatBoxWidth = stageWidth - (margin * 2)  // Full width minus left and right margins
        val chatHistoryHeight = stageHeight * 0.7  // 70% of screen height
        val remainingHeight = stageHeight - (margin * 3) - chatHistoryHeight  // Remaining space minus margins
        val inputHeight = if (remainingHeight > 0) remainingHeight else stageHeight * 0.15  // Use remaining space or default

        // 1. Background
        background = solidRect(stageWidth, stageHeight, Colors["#222222"].withAd(0.8))

        // 2. NPC Header
        title = uiText("Talking to:") {
            position(margin, margin)
        }

        // 3. Scrollable History (70% of height, 5% margin at top and left)
        scrollable = uiScrollable(Size(chatBoxWidth.toInt(), chatHistoryHeight.toInt())) {
            this.addChild(chatHistory)
        }
        scrollable.position(margin, margin * 2 + 20)  // Below header with margin

        // 4. Text Input (5% margin spacing from history, 5% margin bottom, left, right)
        inputField = uiTextInput(initialText = "", Size(chatBoxWidth.toInt(), inputHeight.toInt())) {
            position(margin, margin + chatHistoryHeight + (margin * 1.5))

            // Handle "Enter" key
            onReturnPressed {
                val message = text
                if (message.isNotBlank()) {
                    chatManager.sendUserMessage(message)
                    text = "" // Clear input
                }
            }
            onEscPressed {
                this@ChatBox.visible(false)
            }
        }

    }

    fun setTitle(text: String) {
        title.text(text)
    }

    fun clear() {
        chatHistory.removeChildren()
        title.removeChildren()
    }

    fun addMessage(message: ChatMessage) {
        val name = if (message.role == MessageSender.PLAYER) "You" else npcName
        chatHistory.uiText("$name: ${message.text}") {
            width = 380.0
        }
    }

}