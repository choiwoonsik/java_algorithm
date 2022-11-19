package 그래프탐색

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/*
9
7 3
7
1 2
1 3
2 7
2 8
2 9
4 5
4 6
 */

private var board = Array(0) { Array(0) { 0 } }
private var visited = Array(0) { false }
private var count = Array(0) { 0 }
private var N: Int = 0
private var P: Int = 0
private var Q: Int = 0
private var M: Int = 0

fun main() {
    var br = BufferedReader(InputStreamReader(System.`in`))
    N = br.readLine().toInt()
    var st = StringTokenizer(br.readLine())
    P = st.nextToken().toInt()
    Q = st.nextToken().toInt()
    M = br.readLine().toInt()

    board = Array(N + 1) { Array(N + 1) { 0 } }
    visited = Array(N + 1) { false }
    count = Array(N + 1) { 0 }

    for (i in 1..M) {
        st = StringTokenizer(br.readLine())
        var u = st.nextToken().toInt()
        var v = st.nextToken().toInt()
        board[u][v] = 1
        board[v][u] = 1
    }

    print(bfs())
}

private fun bfs(): Int {
    var queue = LinkedList<Int>()
    queue.add(P)
    visited[P] = true

    while (queue.isNotEmpty()) {
        var cur = queue.poll()

        if (cur == Q) {
            return count[Q]
        }

        for (next in 1..N) {
            if (board[cur][next] == 1 && !visited[next]) {
                visited[next] = true
                count[next] = count[cur] + 1
                queue.add(next)
            }
        }
    }

    return -1
}
