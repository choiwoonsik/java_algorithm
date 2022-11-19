package 그래프탐색

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer

/*
4 5 1
1 2
1 3
1 4
2 4
3 4
 */
class `DFS+BFS`

private var N: Int = 0
private var M: Int = 0
private var V: Int = 0
private var board = Array(0) { Array(0) { 0 } }
private var visited = Array(0) { false }
private var answer: StringBuilder = StringBuilder()

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    V = st.nextToken().toInt()
    board = Array(N + 1) { Array(N + 1) { 0 } }
    visited = Array(N + 1) { false }

    for (i in 0 until M) {
        st = StringTokenizer(br.readLine())
        val y = st.nextToken().toInt()
        val x = st.nextToken().toInt()
        board[y][x] = 1
        board[x][y] = 1
    }

    dfs(V)
    init()
    bfs(V)
    println(answer)
}

private fun init() {
    answer.append("\n")
    visited.fill(false)
}

private fun dfs(now: Int) {
    if (!visited[now]) {
        answer.append("$now ")
        visited[now] = true
    }

    for (next in 1..N) {
        if (!visited[next] && board[now][next] == 1) dfs(next)
    }
}

private fun bfs(start: Int) {
    val queue = LinkedList<Int>()
    queue.add(start)
    visited[start] = true

    while (!queue.isEmpty()) {
        val now = queue.poll()
        answer.append("$now ")

        for (next in 1..N) {
            if (!visited[next] && board[now][next] == 1) {
                visited[next] = true
                queue.add(next)
            }
        }
    }
}