package 그래프탐색

import java.io.*
import java.util.*

class 단지번호붙이기_2667

/*
7
0110100
0110101
1110101
0000111
0100000
0111110
0111000
 */

private var board = Array(0) { Array(0) { 0 } }
private var visited = Array(0) { Array(0) { false } }
private var N = 0
private var C = 0
private var sb = StringBuilder()

fun main() {
    var br = BufferedReader(InputStreamReader(System.`in`))
    N = br.readLine().toInt()

    board = Array(N + 1) { Array(N + 1) { 0 } }
    visited = Array(N + 1) { Array(N + 1) { false } }

    for (i in 0 until N) {
        var line = br.readLine().toCharArray()
        for (j in 0 until N) {
            board[i][j] = line[j] - '0'
        }
    }

    var list = mutableListOf<Int>()

    for (i in 0 until N) {
        for (j in 0 until N) {
            if (board[i][j] == 1 && !visited[i][j]) {
                visited[i][j] = true
                list.add(bfs(Pair(i, j)))
                C++
            }
        }
    }

    sb.append("$C\n")
    list.sort()
    list.map { sb.append("$it\n") }
    print(sb)
}

private fun bfs(start: Pair<Int, Int>): Int {
    var count = 0
    var queue = LinkedList<Pair<Int, Int>>()
    queue.add(start)

    var dy = arrayOf(0, 0, 1, -1)
    var dx = arrayOf(-1, 1, 0, 0)

    while (queue.isNotEmpty()) {
        var cur = queue.poll()
        count++

        for (d in 0 until 4) {
            var ny = cur.first + dy[d]
            var nx = cur.second + dx[d]

            if (ny >= N || nx >= N || ny < 0 || nx < 0) continue
            if (visited[ny][nx]) continue
            if (board[ny][nx] == 1) {
                visited[ny][nx] = true
                queue.add(Pair(ny, nx))
            }
        }
    }
    return count
}