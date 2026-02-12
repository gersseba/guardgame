package com.gersseba.guardgame.models

class Door(var x: Double, var y: Double, var isSafe: Boolean) {

    fun open(): String {
        return if (isSafe) {
            "You opened the safe door!"
        } else {
            "You opened the dangerous door!"
        }
    }

}
