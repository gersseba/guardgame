package com.gersseba.guardgame.models

import java.util.Random

class World(
    val width: Double = 100.0,
    val height: Double = 100.0,

    val player: Player = Player(50.0, 50.0),
    val random: Random = Random(),
    val guardRandom: Int = random.nextInt(2),
    val guards: List<Guard> = listOf(
        Guard(45.0, 45.0, tellsTruth = guardRandom == 0),
        Guard(55.0, 45.0, tellsTruth = guardRandom == 1)
    ),
    val doorRandom: Int = random.nextInt(2),
    val doors: List<Door> = listOf(
        Door(45.0, 40.0, isSafe = doorRandom == 0),
        Door(55.0, 40.0, isSafe = doorRandom == 1)
    ),
) {

    fun findClosestNonPlayerModel(): BaseModel? {
        val models : List<BaseModel> = guards + doors

        return models.minByOrNull { model ->
            Math.sqrt(Math.pow(player.x - model.x, 2.0) + Math.pow(player.y - model.y, 2.0))
        }
    }
}
