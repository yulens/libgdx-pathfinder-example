package io.github.yulens.libgdxPathfind.pathfind

import com.badlogic.gdx.ai.pfa.Connection
import com.badlogic.gdx.utils.Array

class Node(x: Int, y: Int, type: Int, connectionCapacity: Int) :
        AbstractNode<Node>(x, y, type, Array(connectionCapacity))


abstract class AbstractNode<N : AbstractNode<N>>
(val x: Int, val y: Int, val nodeType: Int, var connections: Array<Connection<N>>) {
    var index = -1
}