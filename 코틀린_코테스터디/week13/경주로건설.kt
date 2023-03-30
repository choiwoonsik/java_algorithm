package 코틀린_코테스터디.week13

import 코틀린_코테스터디.week13.Solution.Direction.*
import java.lang.IllegalArgumentException
import kotlin.math.min

/*
직선 도로 : 100원
코너: 500
 */
class Solution {
    companion object {
        private var LEN: Int = 0
        private const val EMPTY = 0
        private const val WALL = 1
        private const val STRAIGHT = 100
        private const val CORNER = 500
    }

    private val dy = arrayOf(0, 0, -1, 1) // right, left, up, down
    private val dx = arrayOf(1, -1, 0, 0)
    private var minCost = Int.MAX_VALUE
    private lateinit var map: Array<IntArray>
    private lateinit var dp: Array<Array<IntArray>>

    fun solution(board: Array<IntArray>): Int {
        map = board.clone()
        dp = Array(map.size) { Array(map.size) { IntArray(5) { minCost } } }
        LEN = map.size

        val start = Dot(0, 0)
        val end = Dot(map.size - 1, map.size - 1)

        dp[0][0][H.dir] = 0
        dfs(start, end, 0, H)

        return minCost
    }

    private fun dfs(cur: Dot, end: Dot, cost: Int, beforeDir: Direction) {
        if (cur.y == end.y && cur.x == end.x) {
            minCost = min(minCost, cost)
            return
        }

        if (cost >= minCost) return

        for (dir in 0 until 4) {
            val ny = cur.y + dy[dir]
            val nx = cur.x + dx[dir]

            if (ny >= LEN || nx >= LEN || ny < 0 || nx < 0) continue
            if (map[ny][nx] == WALL) continue
            val goDir = Direction.getCur(dir)

            if (beforeDir == H) {
                dp[ny][nx][goDir.dir] = dp[cur.y][cur.x][beforeDir.dir] + STRAIGHT
                dfs(Dot(ny, nx), end, dp[ny][nx][goDir.dir], goDir)
            } else {
                when (beforeDir) {
                    R, L -> {
                        val plusCost = if (goDir == D || goDir == U) CORNER + STRAIGHT else STRAIGHT

                        if (dp[ny][nx][goDir.dir] > dp[cur.y][cur.x][beforeDir.dir] + plusCost) {
                            dp[ny][nx][goDir.dir] = dp[cur.y][cur.x][beforeDir.dir] + plusCost
                            dfs(Dot(ny, nx), end, dp[ny][nx][goDir.dir], goDir)
                        }
                    }

                    U, D -> {
                        val plusCost = if (goDir == R || goDir == L) CORNER + STRAIGHT else STRAIGHT

                        if (dp[ny][nx][goDir.dir] > dp[cur.y][cur.x][beforeDir.dir] + plusCost) {
                            dp[ny][nx][goDir.dir] = dp[cur.y][cur.x][beforeDir.dir] + plusCost
                            dfs(Dot(ny, nx), end, dp[ny][nx][goDir.dir], goDir)
                        }
                    }

                    else -> {
                        throw IllegalArgumentException("이게 뭐람? $this")
                    }
                }
            }
        }
    }

    private data class Dot(
        val y: Int,
        val x: Int,
    )

    private enum class Direction(val dir: Int) {
        R(0), L(1), U(2), D(3), H(4);

        companion object {
            fun getCur(dir: Int): Direction {
                val value = values().firstOrNull { it.dir == dir }
                return value ?: H
            }
        }
    }
}

fun main() {
    Solution().solution(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0)))
}