package 그래프탐색

import 그래프탐색.빙산_2573.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer

class 빙산_2573 {
    data class Ice(
        var y: Int,
        var x: Int
    )
}
/*
5 7
0 0 0 0 0 0 0
0 2 4 5 3 0 0
0 3 0 2 5 2 0
0 7 6 2 4 0 0
0 0 0 0 0 0 0
 */

private var board = Array(0) { Array(0) { 0 } }
private var nextBoard = Array(0) { Array(0) { 0 } }
private var visited = Array(0) { Array(0) { false } }
private var N: Int = 0
private var M: Int = 0
private var queue = LinkedList<Ice>()
private var start: Ice = Ice(0, 0)
private var dy = listOf(0, 0, -1, 1)
private var dx = listOf(-1, 1, 0, 0)

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    board = Array(N + 1) {Array(M + 1) { 0 } }
    nextBoard = Array(N + 1) {Array(M + 1) { 0 } }
    visited = Array(N +1) { Array(M + 1) { false } }

    for (i in 0 until N) {
        st = StringTokenizer(br.readLine())
        for (j in 0 until M) {
            var ice = st.nextToken().toInt()
            board[i][j] = ice
            if (ice > 0) start = Ice(i, j)
        }
    }

    print(solution())
}

private fun solution(): Int {
    var year = 0
    while (true) {
        meltingIceBFS()
        year++
        visited.map { it.fill(false) }
        nextBoard.mapIndexed { index, _ ->
            board[index] = nextBoard[index].clone()
        }
        nextBoard.map { it.fill(0) }

        var result = checkDivided()
        if (result >= 2) return year
        else if (result == 0) return 0
        visited.map { it.fill(false) }
    }
}

private fun meltingIceBFS() {
    queue.add(start)

    while (queue.isNotEmpty()) {
        var cur = queue.poll()

        for (dir in 0..3) {
            var ny = cur.y + dy[dir]
            var nx = cur.x + dx[dir]

            if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue
            if (visited[ny][nx]) continue
            if (board[ny][nx] == 0) continue

            visited[ny][nx] = true
            calculateMelting(ny, nx)
            queue.add(Ice(ny, nx))
        }
    }
}

private fun calculateMelting(y: Int, x: Int) {
    var count = 0

    for (dir in 0..3) {
        var ny = y + dy[dir]
        var nx = x + dx[dir]

        if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue
        if (board[ny][nx] == 0) count++
    }

    nextBoard[y][x] = if (board[y][x] - count < 0) 0 else board[y][x] - count
}

private fun checkDivided(): Int {
    var iceLandCnt = 0

    for (y in 0 until N) {
        for (x in 0 until M) {
            if (board[y][x] >= 1 && !visited[y][x]) {
                checkDividedDFS(Ice(y, x))
                iceLandCnt++
                start = Ice(y, x)
            }
        }
    }

    return iceLandCnt
}

private fun checkDividedDFS(cur: Ice) {
    if (visited[cur.y][cur.x]) return

    visited[cur.y][cur.x] = true

    for (dir in 0..3) {
        var ny = cur.y + dy[dir]
        var nx = cur.x + dx[dir]

        if (board[ny][nx] >= 1 && !visited[ny][nx]) {
            checkDividedDFS(Ice(ny, nx))
        }
    }
}
