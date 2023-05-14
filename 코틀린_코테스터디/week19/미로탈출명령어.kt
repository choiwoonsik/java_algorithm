package 코틀린_코테스터디.week19

import kotlin.math.abs

class Solution미래탈출명령어 {
    companion object {
        private val dy = listOf(1, 0, 0, -1)
        private val dx = listOf(0, -1, 1, 0)
        private val dir = listOf('d', 'l', 'r', 'u')
        private var stop = false
        private var answer = ""
        private var N: Int = 0
        private var M: Int = 0
        private var ey: Int = 0
        private var ex: Int = 0
    }

    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        N = n
        M = m
        ey = r
        ex = c

        if (!canGo(x, y, k)) return "impossible"
        dfs(x, y, k - 1, "")
        return answer
    }

    private fun canGo(cy: Int, cx: Int, k: Int): Boolean {
        val dist = abs(cy - ey) + abs(cx - ex)
        if (dist > k) return false
        if ((k - dist) % 2 != 0) return false
        return true
    }

    private fun dfs(cy: Int, cx: Int, count: Int, history: String) {
        if (stop || count < 0) return

        for (d in 0..3) {
            if (stop) return
            val ny = cy + dy[d]
            val nx = cx + dx[d]

            if (ny < 0 || ny > N || nx < 0 || nx > M) continue
            if (!canGo(ny, nx, count)) continue
            if (count > 0) dfs(ny, nx, count - 1, history + dir[d])
            if (cy == ey && cx == ex && count == 0) {
                stop = true
                answer = history + dir[d]
                return
            }
        }
    }
}

fun main() {
    print(Solution미래탈출명령어().solution(3, 4, 2, 3, 3, 1, 5))
}