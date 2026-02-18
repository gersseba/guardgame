package com.gersseba.guardgame.logic

import com.gersseba.guardgame.models.World
import com.gersseba.guardgame.ui.ChatBox
import korlibs.event.Key
import korlibs.korge.input.keys
import korlibs.korge.view.View
import korlibs.korge.view.visible

class Controls {

    fun registerKeyPress(stage: View, chatUI : ChatBox, world: World) {

        stage.keys {
            down(Key.UP) { world.player.y -= 1.0 }
            down(Key.DOWN) { world.player.y += 1.0 }
            down(Key.LEFT) { world.player.x -= 1.0 }
            down(Key.RIGHT) { world.player.x += 1.0 }
            down(Key.E) { interact(world, chatUI) }
            down(Key.ESCAPE) { chatUI.visible(false) }
        }
    }

    fun interact(world: World, chatUi: ChatBox) {
        world.findClosestNonPlayerModel()
            ?.takeIf { it.isCloseTo(world.player) }
            ?.let { nearbyNpc ->
                if (nearbyNpc != null && !chatUi.visible) {
                    chatUi.visible(true)
                }
            }
    }
}