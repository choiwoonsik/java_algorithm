package 그래프탐색

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class 토마토_7569
/*
5 3 2
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 1 0 0
0 0 0 0 0
 */

private var board = Array(0) { Array(0) { Array(0) { 0 } } }
private var X = 0
private var Y = 0
private var H = 0
private var queue = LinkedList<Triple<Int, Int, Int>>()
private var initialAppleCount = 0
private var blankCnt = 0
private var plusCnt = 0

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    X = st.nextToken().toInt()
    Y = st.nextToken().toInt()
    H = st.nextToken().toInt()

    board = Array(H + 1) { Array(Y + 1) { Array(X + 1) { 0 } } }

    for (h in 0 until H) {
        for (i in 0 until Y) {
            st = StringTokenizer(br.readLine())
            for (j in 0 until X) {
                board[h][i][j] = st.nextToken().toInt()
                if (board[h][i][j] == 1) {
                    initialAppleCount++
                    queue.add(Triple(h, i, j))
                }
                if (board[h][i][j] == -1) blankCnt++
            }
        }
    }

    var answer: Int
    if (initialAppleCount + blankCnt == H * X * Y) {
        print(0)
        return
    }
    answer = bfs()
    if (initialAppleCount + blankCnt + plusCnt < H * X * Y) answer = -1
    print(answer)
}

private fun bfs(): Int {

    var tmpQueue = LinkedList<Triple<Int, Int, Int>>()
    var dy = listOf(0, 0, -1, 1)
    var dx = listOf(-1, 1, 0, 0)
    var dh = listOf(0, 1, -1)
    var days = 0

    while (queue.isNotEmpty()) {
        var curSize = queue.size

        while (curSize-- > 0) {
            var cur = queue.poll()

            for (height in dh.indices) {
                for (dir in dy.indices) {
                    var nh = cur.first + dh[height]
                    var ny = cur.second
                    var nx = cur.third
                    if (height == 0) {
                        ny = cur.second + dy[dir]
                        nx = cur.third + dx[dir]
                    }

                    if (nh < 0 || nh >= H || ny < 0 || ny >= Y || nx < 0 || nx >= X) continue
                    if (board[nh][ny][nx] == 1) continue
                    if (board[nh][ny][nx] == -1) continue

                    board[nh][ny][nx] = 1
                    tmpQueue.add(Triple(nh, ny, nx))
                }
            }
        }
        if (tmpQueue.isNotEmpty()) {
            plusCnt += tmpQueue.size
            queue.addAll(tmpQueue)
            tmpQueue.clear()
            days++
        }
    }

    return days
}