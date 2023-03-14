package 코틀린_코테스터디.week9

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
3 7
32 62
42 68
12 98
95 13
97 25
93 37
79 27
75 19
49 47
67 17
 */

class 뱀과사다리게임_16928 {
    private lateinit var bridgeMap: MutableMap<Int, Int>
    private lateinit var snakeMap: MutableMap<Int, Int>
    private lateinit var board: IntArray
    private lateinit var dp: IntArray

    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        val bN = st.nextToken().toInt()
        val sN = st.nextToken().toInt()

        bridgeMap = mutableMapOf()
        snakeMap = mutableMapOf()

        board = IntArray(101)
        dp = IntArray(101)

        board.fill(Int.MAX_VALUE)
        dp.fill(Int.MAX_VALUE)

        repeat(bN) {
            st = StringTokenizer(br.readLine())
            val from = st.nextToken().toInt()
            val to = st.nextToken().toInt()
            bridgeMap[from] = to
        }

        repeat(sN) {
            st = StringTokenizer(br.readLine())
            val from = st.nextToken().toInt()
            val to = st.nextToken().toInt()
            snakeMap[from] = to
        }

        board[1] = 0
        dfs(1)
    }

    private fun dfs(now: Int) {
        if (now == 100) {
            println(dp[now])
            return
        }

        dp[now] += 1
        if (bridgeMap[now] != null) {
            val next = bridgeMap[now]!!
            dp[next] = dp[now]
            dfs(next)
        }

        if (snakeMap[now] != null) {
            val next = snakeMap[now]!!
            dp[next] = dp[now]
            dfs(next)
        }

        for (dice in 1..6) {
            if (now + dice > 100) continue

            if (dp[now] + 1 >= dp[now + dice]) {
                dfs(now + dice)
            }
        }
    }
}

fun main() {
    val problem = 뱀과사다리게임_16928()
    problem.solution()
}