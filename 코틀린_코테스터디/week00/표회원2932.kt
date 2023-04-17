package 코틀린_코테스터디.week00

import java.util.StringTokenizer


/*
4 2
6 3 4
6 2 2
 */

class Solution2932 {
    companion object {
        private lateinit var nodes: MutableList<Node>
        private var N: Int = 0
        private var T: Int = 0
    }

    fun solution() {
        val line = readln().split(" ")
        N = line[0].toInt()
        T = line[1].toInt()

        nodes = mutableListOf()
        repeat(T) {
            val st = StringTokenizer(readln())

            val num = st.nextToken().toInt() - 1
            val ny = st.nextToken().toInt() - 1
            val nx = st.nextToken().toInt() - 1
            val cy = num / N
            val cx = num % N
            nodes.add(Node(num, cy, cx, ny, nx))
        }

        val answer = StringBuilder()
        for(node in nodes) {
            answer.append(move(node)).append("\n")
        }
        print(answer)
    }

    private fun move(cur: Node): Int {
        val (num, cy, cx, ny, nx) = cur

        val xMove = if (cx <= nx) nx - cx else N - cx + nx
        val yMove = if (cy <= ny) ny - cy else N - cy + ny

        for (next in nodes) {
            if (next.cy == cur.cy) next.cx = (next.cx + xMove) % N
        }

        for (next in nodes) {
            if (next.cx == cur.cx) next.cy = (next.cy + yMove) % N
        }

        return xMove + yMove
    }

    private data class Node(
        val num: Int,
        var cy: Int,
        var cx: Int,
        val ny: Int,
        val nx: Int,
    )
}

fun main() {
    Solution2932().solution()
}