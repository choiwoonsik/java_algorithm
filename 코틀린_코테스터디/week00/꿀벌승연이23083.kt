package 코틀린_코테스터디.week00

import java.util.StringTokenizer
import kotlin.math.pow

class Solution23083 {
    companion object {
        private var N: Int = 0
        private var M: Int = 0
        private var K: Int = 0
        private val O = 10.0.pow(9.0) + 7
        private lateinit var dp: Array<LongArray>
    }

    fun solution() {
        val line = readln().split(" ")
        N = line[0].toInt()
        M = line[1].toInt()
        K = readln().toInt()
        dp = Array(N + 2) { LongArray(M + 1) { -1 } }

        init()
        for (i in 0 until K) {
            val st = StringTokenizer(readln())
            val y = st.nextToken().toInt()
            val x = st.nextToken().toInt()
            dp[y][x] = 0
        }
        print(find(N, M))
    }

    private fun init() {
        for (i in 0..N + 1) {
            dp[i][0] = 0
        }
        for (j in 0..M) {
            dp[0][j] = 0
            dp[N + 1][j] = 0
        }
        dp[1][1] = 1
    }

    private fun find(y: Int, x: Int): Long {
        if (dp[y][x] != -1L) return dp[y][x]
        var next = find(y - 1, x) + find(y, x - 1)
        next += if (x % 2 == 0) find(y + 1, x - 1) else find(y - 1, x - 1)
        dp[y][x] = (next % O).toLong()
        return dp[y][x]
    }
}

fun main() {
    Solution23083().solution()
}