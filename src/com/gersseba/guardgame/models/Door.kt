package com.gersseba.guardgame.models

class Door(x: Double, y: Double, var isSafe: Boolean) : BaseModel(x,y) {

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
