package com.gersseba.guardgame.models

class Guard(var x: Double, var y: Double, var tellsTruth: Boolean) {

    fun respondToQuestion(question: String): String {
        return if (tellsTruth) {
            "The answer is: truth}"
        } else {
            "The answer is: lie}"
        }
    }

}
