package 코틀린_코테스터디.week17

import java.util.*
import kotlin.math.min

/*
3
5 3
3 2
2 6
 */

class Solution11049 {
    companion object {
        private var N: Int = 0
        private lateinit var dp: Array<IntArray> // dp[1][n] = 1~n 구간의 최소 곱셈 수
        private lateinit var matrix: Array<IntArray> // 행렬배열
        private const val R = 0
        private const val C = 1
    }

    fun solution() {
        N = readln().toInt()
        dp = Array(N + 1) { IntArray(N + 1) }
        matrix = Array(N + 1) { IntArray(2) }

        for (i in 1..N) {
            val st = StringTokenizer(readln())
            matrix[i][R] = st.nextToken().toInt()
            matrix[i][C] = st.nextToken().toInt()
        }

        go()
    }

    private fun go() {
        // 구할 행렬 범위 1 ~ N - 1
        for (r in 1 until N) {
            // 계산을 시작할 행렬 위치, 범위가 1 시작이 0이면 0~N-1까지 시작점
            for (s in 1..N - r) {
                dp[s][s + r] = Int.MAX_VALUE
                // 범위 r내에서 분할 지점을 옮김
                for (c in s until s + r) {
                    // s ~ s+r 범위의 최소 곱셈수 갱신
                    dp[s][s + r] = min(
                        // (s~r 행렬 곱셈수) + (c+1~s+r 행렬 곱셈수) + (s~r * c+1~s+r 행렬의 곱셈수)
                        dp[s][s + r], dp[s][c] + dp[c + 1][s + r] + matrix[s][R] * matrix[c][C] * matrix[s + r][C]
                    )
                }
            }
        }

        print(dp[1][N])
    }
}

fun main() {
    Solution11049().solution()
}