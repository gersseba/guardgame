package com.gersseba.guardgame.models

class Guard(x: Double, y: Double, var tellsTruth: Boolean) : BaseModel(x,y) {

    fun respondToQuestion(question: String): String {
        return if (tellsTruth) {
            "The answer is: truth"
        } else {
            "The answer is: lie"
        }
    }

    override fun interact(player: Player): String {
        return respondToQuestion("")
    }
}
