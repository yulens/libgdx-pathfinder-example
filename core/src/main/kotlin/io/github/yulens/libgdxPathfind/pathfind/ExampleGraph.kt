package io.github.yulens.libgdxPathfind.pathfind

import com.badlogic.gdx.ai.pfa.Connection
import com.badlogic.gdx.ai.pfa.DefaultConnection
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array


class ExampleGraph(private val map: kotlin.Array<kotlin.Array<Int>>) : IndexedGraph<Node> {


    private val graphWidth = map.first().size
    private val graphHeight = map.size
    private var nodes: Array<Node> = Array(graphWidth * graphHeight)


    init {
        createGraph()
    }

    private fun createGraph() {
        var indexCounter = 0
        for (x in 0 until graphWidth) {
            for (y in 0 until graphHeight) {
                nodes.add(Node(x, y, map[x][y], 4).apply { index = indexCounter })
                indexCounter++
            }
        }
        for (x in 0 until graphWidth) {
            val idx = x * graphHeight
            for (y in 0 until graphHeight) {
                val n = nodes.get(idx + y)
                if (x > 0) addConnection(n, -1, 0)
                if (y > 0) addConnection(n, 0, -1)
                if (x < graphWidth - 1) addConnection(n, 1, 0)
                if (y < graphHeight - 1) addConnection(n, 0, 1)
            }
        }
    }

    private fun addConnection(n: Node, xOffset: Int, yOffset: Int) {
        val target = getNode(n.x + xOffset, n.y + yOffset)
        target?.let {
            if (n.nodeType == 0)
                n.connections.add(DefaultConnection<Node>(n, target))
        }
    }

    private fun getNode(x: Int, y: Int): Node? {
        if (x in 0 until graphWidth && y in 0 until graphHeight) {
            return nodes.get(x * graphHeight + y)
        }
        else
            return null
    }

    private fun getNode(vector2: Vector2): Node? {
        return getNode(vector2.x.toInt(), vector2.y.toInt())
    }

    fun findPath(fromCell: Vector2, toCell: Vector2): ExamplePath<Node> {
        val path = ExamplePath<Node>()
        val pathFinder = IndexedAStarPathFinder<Node>(this, true)
        pathFinder.searchNodePath(getNode(fromCell), getNode(toCell), EpsilonHeuristic<Node>(), path)
        return path
    }


    override fun getNodeCount(): Int {
        return nodes.size
    }

    override fun getConnections(fromNode: Node): Array<Connection<Node>> {
        return fromNode.connections
    }

    override fun getIndex(node: Node): Int {
        return node.index
    }

}