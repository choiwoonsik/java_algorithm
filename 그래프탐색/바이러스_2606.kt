package 그래프탐색

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class 바이러스_2606
/*
7
6
1 2
2 3
1 5
5 2
5 6
4 7
 */

private var board = Array(0) { Array(0) { 0 } }
private var visited = Array(0) { false }
private var N: Int = 0
private var M: Int = 0
private var COUNT = 0

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    N = br.readLine().toInt()
    M = br.readLine().toInt()
    board = Array(N + 1) { Array(N + 1) { 0 } }
    visited = Array(N + 1) { false }

    for (i in 1..M) {
        var st = StringTokenizer(br.readLine())

        var from = st.nextToken().toInt()
        var to = st.nextToken().toInt()
        board[from][to] = 1
        board[to][from] = 1
    }

    bfs(1)
    print(COUNT)
}

private fun bfs(start: Int) {
    var queue = LinkedList<Int>()
    queue.add(start)
    visited[start] = true

    while (queue.isNotEmpty()) {
        var cur = queue.poll()

        for (next in 1..N) {
            if (board[cur][next] == 1 && !visited[next]) {
                visited[next] = true
                queue.add(next)
                COUNT++
            }
        }
    }
}