package com.gersseba.guardgame.models

data class World(
    val player: Player,
    val guards: List<Guard>,
    val doors: List<Door>
)
