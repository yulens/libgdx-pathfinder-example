package io.github.yulens.libgdxPathfind.pathfind

import com.badlogic.gdx.ai.pfa.DefaultGraphPath
import com.badlogic.gdx.ai.pfa.SmoothableGraphPath
import com.badlogic.gdx.math.Vector2


class ExamplePath<N : AbstractNode<N>> : DefaultGraphPath<N>(), SmoothableGraphPath<N, Vector2> {

    private val tmpPosition = Vector2()

    override fun getNodePosition(index: Int): Vector2 {
        val node = nodes.get(index)
        return tmpPosition.set(node.x.toFloat(), node.y.toFloat())
    }

    override fun swapNodes(index1: Int, index2: Int) {
        nodes.set(index1, nodes.get(index2))
    }

    override fun truncatePath(newLength: Int) {
        nodes.truncate(newLength)
    }
}
