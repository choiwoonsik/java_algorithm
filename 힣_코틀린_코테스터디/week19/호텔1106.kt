package 힣_코틀린_코테스터디.week19

import java.util.StringTokenizer
import kotlin.math.max

/*
100 6
4 9
9 11
3 4
8 7
1 2
9 8
비용 - 이득
100 3
7 12
20 30
30 60
1000 1
100 1
 */
class Solution1106 {
    companion object {
        private var N: Int = 0
        private var C: Int = 0
        private lateinit var costEarns: Array<CostEarn>
        private lateinit var dp: IntArray
    }

    fun solution() {
        val st = StringTokenizer(readln())
        C = st.nextToken().toInt()
        N = st.nextToken().toInt()
        costEarns = Array(N) { CostEarn(0, 0) }
        dp = IntArray(100001) { 0 } // 각 비용에서 벌 수 있는 최대

        var maxCost = C
        for (n in 0 until N) {
            val l = StringTokenizer(readln())
            val c = l.nextToken().toInt()
            val e = l.nextToken().toInt()
            maxCost = max(maxCost, c)
            costEarns[n] = CostEarn(c, e)
        }

        for (c in 1 until dp.size) {
            for (costEarn in costEarns) {
                if (c >= costEarn.cost) {
                    dp[c] = max(dp[c], dp[c - costEarn.cost] + costEarn.earn)
                }
                if (dp[c] >= C) {
                    print(c)
                    return
                }
            }
        }
    }

    private data class CostEarn(
        val cost: Int,
        val earn: Int,
    )
}

fun main() {
    Solution1106().solution()
}