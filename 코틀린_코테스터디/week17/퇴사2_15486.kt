package 코틀린_코테스터디.week17

import kotlin.math.max

class Solution25486 {

    /*
    dp[0] = 0
    today = 0
    day 1~n
    dp[day + t - 1] = max(dp[day - 1] + p)

     */
    companion object {
        private lateinit var dp: IntArray
        private lateinit var days: Array<Pair<Int, Int>>
    }

    fun solution() {
        val n = readln().toInt()
        dp = IntArray(n + 1)
        days = Array(n + 1) { Pair(0, 0) }

        for (i in 1..n) {
            val l = readln().split(" ")
            days[i] = Pair(l[0].toInt(), l[1].toInt())
        }

        for (day in 1..n) {
            val t = days[day].first
            val p = days[day].second

            if (day >= 2) dp[day - 1] = max(dp[day - 1], dp[day - 2])
            dp[day] = max(dp[day], dp[day - 1])

            val nextDay = day + t - 1
            if (nextDay > n) continue
            dp[nextDay] = max(dp[day - 1] + p, dp[nextDay])
        }

        println("${dp[n]}")
    }
}

fun main() {
    Solution25486().solution()
}