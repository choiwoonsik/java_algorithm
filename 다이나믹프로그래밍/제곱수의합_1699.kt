package 다이나믹프로그래밍

import kotlin.math.min
import kotlin.math.sqrt

class 제곱수의합_1699 {

    fun solution() {
        val n = readln().toInt()

        val dp = IntArray(100001) { Int.MAX_VALUE }

        dp[0] = 0
        dp[1] = 1
        dp[2] = 1 + 1
        dp[3] = 1 + 1 + 1
        dp[4] = 1 // 2^2
//        dp[5] = 2^2 + dp[1]

        for (i in 5..n) {
            /*
            18
            18 - 16(4) = 2
            18 - 9(3) = 9
            18 - 4(2) = 14
            18 - 1(1) = 17
             */
            val sqrt = sqrt(i.toDouble()).toInt()
            for (s in sqrt downTo 1) {
                val remain = i - (s * s)
                dp[i] = min(dp[remain] + 1, dp[i])
            }
        }

        println(dp[n])
    }
}

fun main() {
    val c = 제곱수의합_1699()
    c.solution()
}