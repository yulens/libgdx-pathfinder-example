package io.github.yulens.libgdxPathfind

import com.badlogic.gdx.Screen
import io.github.yulens.libgdxPathfind.pathfind.ExampleGraph
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.math.vec2


class TestGame : KtxScreen {

    val canMove = 0
    val cannotMove = 1

    override fun show() {

        val map = Array(10) { x ->
            println()
            Array(10) { y ->
                if (x in 1..8 && y in 0..8) {
                    print("${x}:$y   $cannotMove   ")
                    cannotMove

                }
                else {
                    print("${x}:$y   $canMove   ")
                    canMove
                }
            }

        }

        val graph = ExampleGraph(map)
        val path = graph.findPath(vec2(0f, 0f), vec2(9f, 3f))

        //  map.forEach {coll->
        //      coll.forEach {
        //          print(it)
        //      }
        //      println()
        //  }

        println()
        path.forEach {
            print("(${it.x};${it.y})->")
        }


    }


    override fun render(delta: Float) {

    }

}

class MainGame : KtxGame<Screen>() {
    override fun create() {
        addScreen(TestGame())
        setScreen<TestGame>()
    }

}