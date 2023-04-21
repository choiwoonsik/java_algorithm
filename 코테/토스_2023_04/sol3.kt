package 코테.토스_2023_04

import kotlin.math.max

class SolutionToss3 {

    companion object {
        private const val MAX = Long.MAX_VALUE / 2 - 1
        private lateinit var dp: LongArray
        private lateinit var used: BooleanArray
    }

    fun solution(money: Int, stocks: Array<LongArray>): Long {
        dp = LongArray(money + 1) { 0 }
        used = BooleanArray(105) { false }
        dp[0] = 0
        stocks.sortedBy { it[1] }

        for (m in 1..money) {
            for ((i, stock) in stocks.withIndex()) {
                // 현금 부족
                val loyalty = stock[0]
                val cost = stock[1].toInt()
                if (used[i]) continue
                if (m - cost < 0) continue
                used[i] = true
                dp[m] += max(dp[m], dp[m - cost] + loyalty)
                break
            }
        }

        return if (dp[money] == 0L) 0
        else dp[money]
    }

}

fun main() {
    SolutionToss3().solution(
        10,
        arrayOf(longArrayOf(1L, 1L), longArrayOf(3L, 5L), longArrayOf(3, 5), longArrayOf(4, 9))
    )
}