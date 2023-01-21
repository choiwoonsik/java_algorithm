package 코틀린_코테스터디.week3

import java.util.*

private lateinit var nodes: Array<Node>
private lateinit var stack: Stack<Node>
private var LAST: Int = 0
private var HEAD: Int = -1
private var cursur: Int = 0

class Solution {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        cursur = k
        LAST = n
        nodes = Array(n + 1) { Node() }
        stack = Stack()

        repeat(n) { i ->
            if (i == n - 1) {
                nodes[i] = Node(i, i-1, LAST)
            } else {
                nodes[i] = Node(i, i-1, i+1)
            }
        }

        play(cmd)
        return calc()
    }

    private fun calc(): String {
        var sb = StringBuilder()

        for (i in 0 until LAST) {
            if (nodes[i].deleted.not()) sb.append("O")
            else sb.append("X")
        }

        return sb.toString()
    }

    private fun play(cmd: Array<String>) {
        for (command in cmd) {
            val dir = command.split(" ")[0]
            var move = 0

            if (dir == "U" || dir == "D") move = command.split(" ")[1].toInt()

            when(dir) {
                "U" -> up(move)
                "D" -> down(move)
                "C" -> clear()
                "Z" -> ctrlZ()
                else -> throw IllegalArgumentException("wrong input")
            }
        }
    }

    private fun up(move: Int) {
        repeat(move) {
            cursur = nodes[cursur].front
        }
    }

    private fun down(move: Int) {
        repeat(move) {
            cursur = nodes[cursur].back
        }
    }

    private fun clear() {
        nodes[cursur].deleted = true
        stack.push(nodes[cursur])

        if (nodes[cursur].back == LAST) {
            // last node
            var frontNode = nodes[nodes[cursur].front]
            frontNode.back = LAST

            cursur = frontNode.idx
        } else if (nodes[cursur].front == HEAD) {
            // first node
            var backNode = nodes[nodes[cursur].back]
            backNode.front = HEAD

            cursur = backNode.idx
        } else {
            // middle node
            var frontNode = nodes[nodes[cursur].front]
            var backNode = nodes[nodes[cursur].back]

            frontNode.back = backNode.idx
            backNode.front = frontNode.idx

            cursur = backNode.idx
        }
    }

    private fun ctrlZ() {
        var curNode = stack.pop()
        var curPos = curNode.idx
        curNode.deleted = false

        if (curNode.back == LAST) {
            var frontNode = nodes[curNode.front]

            frontNode.back = curNode.idx
            curNode.front = frontNode.idx

            curNode.back = LAST
        } else if (curNode.front == HEAD) {
            var backNode = nodes[curNode.back]

            backNode.front = curNode.idx
            curNode.back = backNode.idx

            curNode.front = HEAD
        } else {
            var frontNode = nodes[curNode.front]
            var backNode = nodes[curNode.back]

            frontNode.back = curNode.idx
            curNode.front = frontNode.idx

            backNode.front = curNode.idx
            curNode.back = backNode.idx
        }
    }

    private fun findUp(pos: Int): Node {
        return if (nodes[pos].deleted.not()) nodes[pos]
        else findUp(nodes[pos].front)
    }

    private fun findDown(pos: Int): Node {
        return if (nodes[pos].deleted.not()) nodes[pos]
        else findDown(nodes[pos].back)
    }
}

private data class Node(
    var idx: Int = 0,
    var front: Int = 0,
    var back: Int = 0,
    var deleted: Boolean = false
)