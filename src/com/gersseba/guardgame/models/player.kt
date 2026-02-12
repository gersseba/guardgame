package com.gersseba.guardgame.models

class Player(var x: Double, var y: Double) {

    fun move(dx: Double, dy: Double) {
        x += dx
        y += dy
    }

}
