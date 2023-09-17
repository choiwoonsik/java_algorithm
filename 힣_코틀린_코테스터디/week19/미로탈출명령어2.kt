package 힣_코틀린_코테스터디.week19

import kotlin.math.abs

class Solution미래탈출명령어2 {
    //d l r u
    private var dx = intArrayOf(1, 0, 0, -1)
    private var dy = intArrayOf(0, -1, 1, 0)
    private var posChar = arrayOf('d', 'l', 'r', 'u')
    private var stop = false
    private var answer = "impossible"
    private var n = 0
    private var m = 0
    private var sx = 0
    private var sy = 0
    private var ex = 0
    private var ey = 0

    //n x m
    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        this.n = n
        this.m = m
        sx = x - 1
        sy = y - 1
        ex = r - 1
        ey = c - 1
        if (sx == ex && sy == ey) return ""
        if (!canArrival(sx, sy, k)) return answer
        helper(sx, sy, k - 1, "")
        return answer
    }

    //거리, 움직일 횟수
    private fun canArrival(cx: Int, cy: Int, k: Int): Boolean {
        val d = getD(cx, cy, ex, ey)
        return !(d > k || (k - d) % 2 == 1)
    }

    private fun getD(ax: Int, ay: Int, bx: Int, by: Int): Int {
        return abs(ax - bx) + abs(ay - by)
    }

    private fun helper(x: Int, y: Int, cnt: Int, cur: String) {
        if (stop || cnt < 0) return

        for (k in 0..3) {
            if (stop) return
            val nx = x + dx[k]
            val ny = y + dy[k]
            if (nx < 0 || ny < 0 || nx > n - 1 || ny > m - 1 || !canArrival(nx, ny, cnt)) continue
            if (cnt > 0) helper(nx, ny, cnt - 1, cur + posChar[k])

            if (nx == ex && ny == ey && cnt == 0) {
                stop = true
                answer = cur + posChar[k]
                return
            }
        }
    }
}