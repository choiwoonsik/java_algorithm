package 다익스트라

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer

/*
3
5 5 4
3 9 1
3 2 7
0
 */

private val MAX = 987654321
private var N: Int = 0
private val dy = arrayOf(-1, 1, 0, 0)
private val dx = arrayOf(0, 0, -1, 1)
private var board = Array(N + 1) { IntArray(N + 1)}
private var dp = Array(N + 1) { IntArray(N + 1) { MAX } }
private var visited = Array(N + 1) { Array(N + 1) { false } }

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val answer = StringBuilder()
    var count = 1

    while (true) {
        N = br.readLine().toInt()
        if (N == 0) break

        board = Array(N + 1) { IntArray(N + 1) }
        dp = Array(N + 1) { IntArray(N + 1) { MAX } }
        visited = Array(N + 1) { Array(N + 1) { false } }

        repeat(N) { y ->
            val st = StringTokenizer(br.readLine())
            repeat(N) { x ->
                board[y][x] = st.nextToken().toInt()
            }
        }
        dp[0][0] = board[0][0]
        dijkstra(0, 0, board[0][0])

        answer.append("Problem $count: ${dp[N - 1][N - 1]}\n")
        count++
    }
    print(answer)
}

private fun dijkstra(y: Int, x: Int, pay: Int) {
    val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
    pq.add(Triple(y, x, pay))

    while (pq.isNotEmpty()) {
        val (curY, curX, curPay) = pq.poll()

        visited[curY][curX] = true

        if (dp[curY][curX] < curPay) continue

        for (dir in 0..3) {
            val ny = dy[dir] + curY
            val nx = dx[dir] + curX
            if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue
            if (visited[ny][nx]) continue
            if (dp[curY][curX] + board[ny][nx] < dp[ny][nx]) {
                dp[ny][nx] = dp[curY][curX] + board[ny][nx]
                pq.add(Triple(ny, nx, dp[ny][nx]))
            }
        }
    }
}
