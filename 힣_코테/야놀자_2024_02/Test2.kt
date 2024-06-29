package 힣_코테.야놀자_2024_02

import java.util.LinkedList

private class Test2 {
    private lateinit var board: Array<Array<Char>>
    private lateinit var visited: Array<Array<Boolean>>
    private val dy = intArrayOf(-1, 1, 0, 0)
    private val dx = intArrayOf(0, 0, -1, 1)
    private val queue = LinkedList<Dot>()
    private var stokeCount = 0

    fun strokesRequired(picture: Array<String>): Int {
        makeBoard(picture)
        val s = mutableSetOf<String>()
        s.clear()
        val array = Array(1) { false }
        val arr = IntArray(2)
        val sorted = arr.sorted()
        bfs()

        return stokeCount
    }

    private fun bfs() {
        for (i in board.indices) {
            for (j in board[0].indices) {
                if (!visited[i][j]) {
                    val color = board[i][j]
                    fill(i, j, color)
                }
            }
        }
    }

    private fun fill(i: Int, j: Int, color: Char) {
        queue.add(Dot(i, j))
        visited[i][j] = true
        stokeCount++

        while (queue.isNotEmpty()) {
            val dot = queue.poll()
            for (dir in 0 until 4) {
                val ny = dot.y + dy[dir]
                val nx = dot.x + dx[dir]
                if (ny < 0 || nx < 0 || ny >= board.size || nx >= board[0].size) continue
                if (board[ny][nx] != color) continue
                if (!visited[ny][nx]) {
                    queue.add(Dot(ny, nx))
                    visited[ny][nx] = true
                }
            }
        }
    }

    private fun makeBoard(picture: Array<String>) {
        for ((i, row) in picture.withIndex()) {
            for ((j, col) in row.withIndex()) {
                board[i][j] = col
            }
        }

        visited = Array(board.size) { Array(board[0].size) { false } }
    }

    private data class Dot(
        val y: Int,
        val x: Int
    )
}

fun main() {

}