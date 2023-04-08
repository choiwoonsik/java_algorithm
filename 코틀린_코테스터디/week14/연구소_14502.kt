package 코틀린_코테스터디.week14

import java.util.LinkedList
import java.util.StringTokenizer
import kotlin.math.max

/*
4 6
0 0 0 0 v 0
1 0 0 v 0 2
1 1 1 0 0 2
0 0 0 v 0 2
 */

class Solution14502 {
    private var row = 0
    private var col = 0
    private var max = 0
    private lateinit var queue: LinkedList<Pair<Int, Int>>
    private lateinit var map: Array<IntArray>

    fun solution() {
        val st = StringTokenizer(readln())
        row = st.nextToken().toInt()
        col = st.nextToken().toInt()
        map = Array(row + 1) { IntArray(col + 1) }
        queue = LinkedList()

        for (y in 0 until row) {
            val line = StringTokenizer(readln())
            for (x in 0 until col) {
                map[y][x] = line.nextToken().toInt()
                if (map[y][x] == 2) queue.add(y to x)
            }
        }

        dfs(0, 0, 0)
        print(max)
    }

    fun dfs(y: Int, curX: Int, depth: Int) {
        var x = curX
        if (depth == 3) {
            max = max(max, calc(row, col))
            return
        }

        for (ny in y until row) {
            for (nx in x until col) {
                if (map[ny][nx] == 0) {
                    map[ny][nx] = 1
                    dfs(y, x + 1, depth + 1)
                    map[ny][nx] = 0
                }
                x = 0
            }
        }
    }

    private fun calc(row: Int, col: Int): Int {
        val visited = spreadBirus(row, col)
        return countNoirus(row, col, visited)
    }

    private fun spreadBirus(row: Int, col: Int): Array<BooleanArray> {
        val dy = arrayOf(-1, 1, 0, 0)
        val dx = arrayOf(0, 0, 1, -1)
        val tmpQueue = LinkedList(queue)
        val visited = Array(row + 1) { BooleanArray(col + 1) }

        while (tmpQueue.isNotEmpty()) {
            val (y, x) = tmpQueue.poll()

            for (dir in 0..3) {
                val ny = y + dy[dir]
                val nx = x + dx[dir]

                if (ny < 0 || ny >= row || nx < 0 || nx >= col) continue
                if (map[ny][nx] == 2) continue
                if (map[ny][nx] == 1) continue
                if (visited[ny][nx]) continue

                visited[ny][nx] = true
                tmpQueue.add(ny to nx)
            }
        }
        return visited
    }

    private fun countNoirus(row: Int, col: Int, visited: Array<BooleanArray>): Int {
        var count = 0
        for (y in 0 until row) {
            for (x in 0 until col) {
                if (!visited[y][x] && map[y][x] == 0) count++
            }
        }
        return count
    }
}

fun main() {
    Solution14502().solution()
}