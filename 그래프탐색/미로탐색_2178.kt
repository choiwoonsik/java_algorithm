package 그래프탐색

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.Comparator

/*
4 6
101111
101010
101011
111011
 */
class 미로탐색_2178

private var board = Array(0) { Array(0) { 0 } }
private var visited = Array(0) { Array(0) { false } }
private var dy = arrayOf(-1, 1, 0, 0)
private var dx = arrayOf(0, 0, -1, 1)
private var N: Int = 0
private var M: Int = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    board = Array(N + 1) { Array(M + 1) { 0 } }
    visited = Array(N + 1) { Array(M + 1) { false } }


    for (i in 1..N) {
        val line = br.readLine().toCharArray()
        for (j in 1..M) {
            board[i][j] = line[j-1] - '0'
        }
    }

    print(bfs(Dot(1, 1, 1)))
}

private fun bfs(start: Dot): Int {
    var queue: PriorityQueue<Dot> = PriorityQueue(Comparator.comparingInt(Dot::count))
    queue.add(start)

    while (!queue.isEmpty()) {
        val now = queue.poll()

        if (now.y == N && now.x == M) {
            return now.count
        }

        for (d in 0 until 4) {
            var ny = now.y + dy[d]
            var nx = now.x + dx[d]

            if (ny > N || nx > M) continue
            if (visited[ny][nx]) continue
            if (board[ny][nx] == 1) {
                visited[ny][nx] = true
                queue.add(Dot(ny, nx, now.count + 1))
            }
        }
    }

    return 0
}

private data class Dot (
    var y: Int,
    var x: Int,
    var count: Int
)