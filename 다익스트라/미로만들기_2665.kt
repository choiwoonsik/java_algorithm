package 다익스트라

import 다익스트라.미로만들기_2665.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/*
8
11100110
11010010
10011010
11101100
01000111
00110001
11011000
11000111
 */

class 미로만들기_2665 {
    data class Node (
        val dot: Pair<Int, Int>,
        val priority: Int,
    )
}

private var N: Int = 0
private var board = Array(0) { Array(0) { 0 } }
private val pq = PriorityQueue<Node>(compareBy { it.priority })
private var visited = Array(0) { Array(0) { false } }

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    N = br.readLine().toInt()
    board = Array(N + 1) { Array(N + 1) { 0 } }
    visited = Array(N + 1) { Array(N + 1) { false } }

    for(i in 0 until N) {
        val line = br.readLine().toCharArray()
        for (j in 0 until N) {
            board[i][j] = line[j] - '0'
        }
    }

    val s = Node((0 to 0), 0)
    pq.add(s)
    print(bfs())
}

private fun bfs(): Int {
    val dy = arrayOf(0, 0, -1, 1)
    val dx = arrayOf(-1, 1, 0, 0)

    while (pq.isNotEmpty()) {
        val (cur, priority) = pq.poll()

        if (cur.first == N-1 && cur.second == N-1) {
            return priority
        }

        for (dir in 0..3) {
            val ny = cur.first + dy[dir]
            val nx = cur.second + dx[dir]

            if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue
            if (visited[ny][nx]) continue
            if (board[ny][nx] == 0) pq.add(Node((ny to nx), priority + 1))
            else if (board[ny][nx] == 1) pq.add(Node((ny to nx), priority))
            visited[ny][nx] = true
        }
    }

    return 0
}