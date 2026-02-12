package com.gersseba.guardgame.representation
import com.gersseba.guardgame.models.BaseModel
import com.gersseba.guardgame.models.Player
import com.gersseba.guardgame.models.World
import korlibs.image.color.*
import korlibs.korge.view.*
import korlibs.korge.view.camera.*
import korlibs.math.geom.*

class WorldRenderer(
    val stage: Stage,
    val viewTiles: Int,
    val tileSize: Double,
    var world: World
) {
    private val registry = mutableMapOf<BaseModel, View>()

    // 1. Create the CameraContainer
    // We set its size to the 'Viewplane' (25x25 tiles)
    private val cameraContainer = stage.cameraContainer(
        Size(viewTiles * tileSize, viewTiles * tileSize)
    )

    fun addEntity(entity: BaseModel, color: RGBA) {
        // 2. Add views to cameraContainer.content instead of a raw container
        val view = cameraContainer.content.circle(0.5 * tileSize, fill = color).centered
        registry[entity] = view
    }

    fun update() {
        // Sync positions
        registry.forEach { (model, view) ->
            view.xy(model.x * tileSize, model.y * tileSize)

        }

        cameraContainer.cameraX = world.player.x * tileSize
        cameraContainer.cameraY = world.player.y * tileSize
    }
}