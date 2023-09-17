package 다이나믹프로그래밍

import kotlin.math.min

class 달나라토끼를위한구매대금지불도우미_17212 {

    fun solution() {
        /*
        1원, 2원, 5원, 7원

        dp[1] = 1, 1원
        dp[2] = 1, 2원
        dp[3] = 2, 1원 + 2원
        dp[4] = 2, 2원 + 2원
        dp[5] = 1, 5원
        dp[6] = 2, 1원 + 5원
        dp[7] = 1, 7원
         */
        val coins = listOf(1, 2, 5, 7)

        val n = readln().toInt()
        val dp = IntArray(100001) { Int.MAX_VALUE }
        dp[0] = 0
        dp[1] = 1
        dp[2] = 1
        dp[3] = 2
        dp[4] = 2
        dp[5] = 1
        dp[6] = 2
        dp[7] = 1

        for (i in 8..n) {
            for (coin in coins) {
                if (i < coin) continue
                dp[i] = min(dp[i - coin] + dp[coin], dp[i])
            }
        }

        println(dp[n])
    }
}

fun main() {
    val c = 달나라토끼를위한구매대금지불도우미_17212()
    c.solution()
}