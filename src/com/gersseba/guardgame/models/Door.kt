package com.gersseba.guardgame.models

import com.gersseba.guardgame.models.common.Interactable

class Door(x: Double, y: Double, var isSafe: Boolean,
           override val name: String
) : BaseModel(x,y), Interactable {

    fun open(): String {
        return if (isSafe) {
            "You opened the safe door!"
        } else {
            "You opened the dangerous door!"
        }
    }

    override fun interact(player: Player): String {
        return open()
    }

}
