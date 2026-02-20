package com.gersseba.guardgame.logic

import com.gersseba.guardgame.chat.ChatManager
import com.gersseba.guardgame.models.World
import com.gersseba.guardgame.models.common.Chattable
import com.gersseba.guardgame.models.common.Interactable
import korlibs.event.Key
import korlibs.korge.input.keys
import korlibs.korge.view.View

class Controls {

    fun registerKeyPress(stage: View, chatManager: ChatManager, world: World) {

        stage.keys {
            down(Key.UP) { world.player.y -= 1.0 }
            down(Key.DOWN) { world.player.y += 1.0 }
            down(Key.LEFT) { world.player.x -= 1.0 }
            down(Key.RIGHT) { world.player.x += 1.0 }
            down(Key.E) { interact(world, chatManager) }
        }
    }

    fun interact(world: World, chatManager: ChatManager) {
        world.findClosestNonPlayerModel()
            ?.takeIf { it.isCloseTo(world.player) }
            ?.let { nearbyNpc ->
                if (nearbyNpc is Chattable) {
                    chatManager.startConversation(nearbyNpc, nearbyNpc.name)
                } else if (nearbyNpc is Interactable) {
                    val result = nearbyNpc.interact(world.player)
                    println(result)
                }
            }
    }
}