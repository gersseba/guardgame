package com.gersseba.guardgame.models

open class BaseModel(var x: Double, var y: Double)  {

    fun isCloseTo(other: BaseModel, threshold: Double = 2.0): Boolean {
        val distance = Math.sqrt(Math.pow(x - other.x, 2.0) + Math.pow(y - other.y, 2.0))
        return distance < threshold
    }

    open fun interact(player: Player): String {
        return "You interacted with a model at ($x, $y)"
    }
}