package 다이나믹프로그래밍

import java.lang.StringBuilder

class 일로만들기2_12852 {

    companion object {
        private lateinit var dp: Array<Info?>
    }

    fun solution() {
        val n = readln().toInt()
        dp = Array(n + 1) { null }
        dp[n] = Info(n, 0, -1)

        for (i in n downTo 2) {
            val cur = dp[i] ?: continue

            if (i % 3 == 0) {
                if (dp[i / 3] == null)
                    dp[i / 3] = Info(n / 3, cur.order + 1, i)
                else if (dp[i / 3]!!.order > cur.order + 1) {
                    dp[i / 3] = Info(n / 3, cur.order + 1, i)
                }
            }

            if (i % 2 == 0) {
                if (dp[i / 2] == null)
                    dp[i / 2] = Info(n / 2, cur.order + 1, i)
                else if (dp[i / 2]!!.order > cur.order + 1) {
                    dp[i / 2] = Info(n / 2, cur.order + 1, i)
                }
            }

            if (dp[i - 1] == null)
                dp[i - 1] = Info(n - 1, cur.order + 1, i)
            else if (dp[i - 1]!!.order > cur.order + 1)
                dp[i - 1] = Info(n - 1, cur.order + 1, i)
        }

        val history = getHistory(1, "1")
        println("${dp[1]!!.order}\n${history}")
    }

    private fun getHistory(now: Int, history: String): String {
        if (dp[now]!!.before == -1) return history
        return getHistory(dp[now]!!.before, "${dp[now]!!.before} $history")
    }

    private data class Info(
        val num: Int,
        val order: Int,
        val before: Int
    )
}

fun main() {
    일로만들기2_12852().solution()
}