package 코틀린_코테스터디.week14

import java.util.StringTokenizer
import kotlin.math.max

/*
5
1 1 0 1 1
0 1 0 0 0
1 0 1 0 1
1 0 0 0 0
1 0 1 1 1
 */

class Solution1799 {
    companion object {
        private var N: Int = 0
        private var MAX: Int = 0
        private var answer: Int = 0
        private val targetList = mutableListOf<Pair<Int, Int>>()
        private lateinit var map: Array<IntArray>
        private lateinit var blocked: Array<BooleanArray>
        private val dy = arrayOf(-1, -1, 1, 1)
        private val dx = arrayOf(-1, 1, -1, 1) // 왼위, 오위, 왼아, 왼아
    }

    fun solution() {
        N = readln().toInt()
        map = Array(N) { IntArray(N) }
        blocked = Array(N) { BooleanArray(N) }

        for (y in 0 until N) {
            val st = StringTokenizer(readln())
            for (x in 0 until N) {
                map[y][x] = st.nextToken().toInt()
                if (map[y][x] == 1) targetList.add(y to x)
            }
        }
        MAX = targetList.size
        dfs(0, 0, 0)

        print(answer)
    }

    private fun dfs(index: Int, set: Int, block: Int) {
        if (set + block == MAX) {
            answer = max(answer, set)
            return
        }

        if ((MAX - (set + block)) + set <= answer) return

        for (target in index until MAX) {
            val (y, x) = targetList[target]
            if (!blocked[y][x] && map[y][x] == 1) {
                map[y][x] = 2
                val plusBlocked = setBlock(y, x)
                dfs(index + 1, set + 1, block + plusBlocked)
                map[y][x] = 1
                unBlock(y, x)
            }
        }
    }

    private fun setBlock(y: Int, x: Int): Int {
        var count = 0

        for (dir in 0..3) {
            var ny: Int = y
            var nx: Int = x
            while (true) {
                ny += dy[dir]
                nx += dx[dir]
                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue
                if (map[ny][nx] == 1 && !blocked[ny][nx]) {
                    blocked[ny][nx] = true
                    count++
                }
            }
        }
        return count
    }

    private fun unBlock(y: Int, x: Int) {
        for (dir in 0..3) {
            var ny: Int = y
            var nx: Int = x
            while (true) {
                ny += dy[dir]
                nx += dx[dir]
                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue
                blocked[ny][nx] = false
            }
        }
    }
}

fun main() {
    Solution1799().solution()
}