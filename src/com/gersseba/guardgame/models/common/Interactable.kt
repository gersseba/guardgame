package com.gersseba.guardgame.models.common

import com.gersseba.guardgame.models.Player

interface Interactable {

        fun interact(player: Player): String
}