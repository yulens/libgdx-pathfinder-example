package io.github.yulens.libgdxPathfind

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import io.github.yulens.libgdxPathfind.pathfind.ExampleGraph
import io.github.yulens.libgdxPathfind.pathfind.ExamplePath
import io.github.yulens.libgdxPathfind.pathfind.Node
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.math.vec2


class TestGame : KtxScreen {

    val map = arrayOf(
            arrayOf(0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,1,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,1,1,1,1,1,1,1,0,0),
            arrayOf(1,0,0,0,0,0,0,1,0,0),
            arrayOf(0,0,0,0,0,0,0,1,0,0),
            arrayOf(0,0,0,0,0,1,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0)
    )
    val startPoint = vec2(0f, 0f)
    val endPoint = vec2(9f, 3f)

    var path = ExamplePath<Node>()
    override fun show() {

        val graph = ExampleGraph(map)
        path = graph.findPath(startPoint,endPoint )

        println()
        path.forEach {
            print("(${it.x};${it.y})->")
        }
    }

    val gridRenderer = ShapeRenderer().apply {
        translate(0f, 230f, 0f)
    }

    val obstacleRenderer = ShapeRenderer().apply {
        translate(0f, 230f, 0f)
    }

    val pathRenderer = ShapeRenderer().apply {
        translate(0f, 230f, 0f)
    }


    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.235f, 0.247f, 0.255f, 0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        gridRenderer.begin(ShapeRenderer.ShapeType.Line)
        gridRenderer.color = Color.GRAY
        drawMap(map)
        drawPath(path)
        gridRenderer.end()
    }

    fun drawPath(path: ExamplePath<Node>) {
        val tileWidth = 100
        val tileHeight = 50

        val halfTileWidth = tileWidth * 0.5f
        val halfTileHeight = tileHeight * 0.5f

        path.forEach {
            val x = it.x * halfTileWidth + it.y * halfTileWidth
            val y = it.y * halfTileHeight - it.x * halfTileHeight

            pathRenderer.begin(ShapeRenderer.ShapeType.Filled)
            pathRenderer.color = Color(  0.212f, 0.616f, 0.318f,0f)
            pathRenderer.rect(x + halfTileWidth / 2, y + halfTileHeight / 2, halfTileWidth, halfTileHeight)
            pathRenderer.end()

        }
    }

    fun drawMap(map: kotlin.Array<kotlin.Array<Int>>) {
        val graphWidth = map.first().size
        val graphHeight = map.size

        val tileWidth = 100
        val tileHeight = 50

        val halfTileWidth = tileWidth * 0.5f
        val halfTileHeight = tileHeight * 0.5f

        for (x_pos in 0 until graphWidth) {
            for (y_pos in graphHeight - 1 downTo 0) {

                val cell = map[x_pos][y_pos]

                val x = x_pos * halfTileWidth + y_pos * halfTileWidth
                val y = y_pos * halfTileHeight - x_pos * halfTileHeight


                gridRenderer.polygon(arrayOf(x, y + halfTileHeight,
                                             x + halfTileWidth, y + halfTileHeight * 2,
                                             x + halfTileWidth * 2, y + halfTileHeight,
                                             x + halfTileWidth, y).toFloatArray())

                if (cell == 1) {
                    obstacleRenderer.begin(ShapeRenderer.ShapeType.Filled)
                    obstacleRenderer.color = Color( 0.714f, 0.341f, 0.294f,0f)
                    obstacleRenderer.rect(x + halfTileWidth / 2, y + halfTileHeight / 2, halfTileWidth, halfTileHeight)
                    obstacleRenderer.end()
                }
            }
        }
    }
}


class MainGame : KtxGame<Screen>() {
    override fun create() {
        addScreen(TestGame())
        setScreen<TestGame>()
    }

}