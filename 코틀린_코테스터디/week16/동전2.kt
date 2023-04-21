package 코틀린_코테스터디.week16

import kotlin.math.min

class Solution2294 {

    companion object {
        private lateinit var dp: IntArray
        private lateinit var coins: IntArray
        private const val MAX = 2000000000
    }

    /*
        dp[n] = dp[n-1] + alpha
        dp[15] = dp[10] + dp[5]
        dp[1] = dp[0] + 1
        dp[2] = dp[2 - c] + 1
    */
    fun solution() {
        val line = readln().split(" ")
        val n = line[0].toInt()
        val k = line[1].toInt()

        dp = IntArray(k + 1) { MAX }
        coins = IntArray(n)
        for (i in 0 until n) {
            val c = readln().toInt()
            coins[i] = c
        }

        dp[0] = 0
        val answer = get(k)
        print(if (answer == MAX) -1 else answer)
    }

    private fun get(k: Int): Int {
        for (money in 1..k) {

            for (coin in coins) {
                if (money - coin >= 0) {
                    if (dp[money - coin] == MAX) continue
                    dp[money] = min(dp[money], dp[money - coin] + 1)
                }
            }
        }

        return dp[k]
    }
}

fun main() {
    Solution2294().solution()
}