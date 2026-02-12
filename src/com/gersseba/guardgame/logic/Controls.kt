package com.gersseba.guardgame.logic

import com.gersseba.guardgame.models.BaseModel
import com.gersseba.guardgame.models.World
import korlibs.event.Key
import korlibs.korge.input.keys
import korlibs.korge.view.View

class Controls {

    fun registerKeyPress(stage: View, world: World) {
        stage.keys {
            down(Key.UP) { world.player.y -= 1.0 }
            down(Key.DOWN) { world.player.y += 1.0 }
            down(Key.LEFT) { world.player.x -= 1.0 }
            down(Key.RIGHT) { world.player.x += 1.0 }
            down(Key.E) { interact(world) }
        }
    }

    fun interact(world: World) {
        world.findClosestNonPlayerModel()
            ?.takeIf { it.isCloseTo(world.player) }
            ?.let { model ->
                val message = model.interact(world.player)
                println(message)
            }
    }
}