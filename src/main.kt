import com.gersseba.guardgame.chat.ChatManager
import com.gersseba.guardgame.chat.GeminiClient
import com.gersseba.guardgame.logic.Controls
import com.gersseba.guardgame.models.World
import com.gersseba.guardgame.representation.WorldRenderer
import com.gersseba.guardgame.ui.ChatBox
import korlibs.image.color.*
import korlibs.korge.*
import korlibs.korge.view.*
import korlibs.math.geom.Size
import korlibs.time.timesPerSecond


suspend fun main() = Korge(windowSize = Size(500, 500), backgroundColor = Colors["#2b2b2b"]) {
    val viewTiles = 25
    val tileSize = width / viewTiles

    // --- MODEL LAYER ---
    val world = World()

    val renderer = WorldRenderer(this, 25, tileSize, world)

    val geminiClient = GeminiClient();
    geminiClient.initConfig()

    val chatUI = ChatBox().addTo(parent = this).visible(false)

    val chatManager = ChatManager(chatUI, geminiClient)
    chatUI.chatManager = chatManager

    // 3. Register entities
    renderer.addEntity(world.player, Colors.BLUE)
    world.guards.forEach { renderer.addEntity(it, Colors.RED) }
    world.doors.forEach { renderer.addEntity(it, Colors.BROWN) }

    Controls().registerKeyPress(this, chatManager, world)

    // Initial render
    addFixedUpdater(30.timesPerSecond) {
        renderer.update()
    }
}