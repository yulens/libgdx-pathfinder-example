package io.github.yulens.libgdxPathfind.pathfind

import com.badlogic.gdx.ai.pfa.Heuristic


class EpsilonHeuristic<N : AbstractNode<N>> : Heuristic<N> {

    override fun estimate(node: N, endNode: N): Float {
        return Math.abs(endNode.x - node.x) + Math.abs(endNode.y - node.y).toFloat()
    }
}
