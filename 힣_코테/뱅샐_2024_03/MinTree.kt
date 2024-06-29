package 힣_코테.뱅샐_2024_03

import kotlin.math.min

class MinTree {
    fun solution() {
        val root = makeTree()

        val min = getMinPath(root, 0)
        println(min)
    }

    private fun getMinPath(curNode: Node?, beforeSum: Int): Int {
        return if (curNode == null) beforeSum
        else if (curNode.isLeafNode()) beforeSum + curNode.node
        else {
            val left = getMinPath(curNode.left, beforeSum + curNode.node)
            val right = getMinPath(curNode.right, beforeSum + curNode.node)
            min(left, right)
        }
    }

    private fun makeTree(): Node {
        val node = Node(10)
            .also {
                it.left = Node(5)
                    .also {
                        it.right = Node(2)
                    }
                it.right = Node(5)
                    .also {
                        it.left = Node(1)
                            .also {
                                it.left = Node(1)
                                    .also {
                                        it.left = Node(-100)
                                    }
                            }
                        it.right = Node(1)
                            .also {
                                it.left = Node(-1)
                            }
                    }
            }

        return node
    }

    class Node(
        val node: Int,
        var left: Node? = null,
        var right: Node? = null,
    ) {
        fun isLeafNode(): Boolean = left == null && right == null
    }
}

fun main() {
    MinTree().solution()
}
