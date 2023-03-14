package 코틀린_코테스터디.week8

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max


class 우수마을_1949 {
    private var N = 0
    private lateinit var dp: Array<Array<Int>>
    private var NICE = 0
    private var NOT_NICE = 1
    private lateinit var visited: Array<Boolean>
    private lateinit var populations: Array<Int>
    private lateinit var adj: Array<MutableList<Int>>

    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st: StringTokenizer

        N = br.readLine().toInt()

        dp = Array(N + 1) { Array(2) { 0 } }
        visited = Array(N + 1) { false }
        populations = Array(N + 1) { 0 }
        adj = Array(N + 1) { (mutableListOf()) }

        for (i in 0 until N + 1) {
            adj[i] = mutableListOf()
        }

        st = StringTokenizer(br.readLine())
        for (i in 1 until N + 1) {
            populations[i] = st.nextToken().toInt()
        }

        for (i in 0 until N - 1) {
            st = StringTokenizer(br.readLine())
            val u = st.nextToken().toInt()
            val v = st.nextToken().toInt()
            adj[u].add(v)
            adj[v].add(u)
        }

        dfs(1)
        println(max(dp[1][NICE], dp[1][NOT_NICE]))
    }

    private fun dfs(root: Int) {
        dp[root][NICE] += populations[root]
        visited[root] = true

        for (neighbors in adj[root]) {
            if (visited[neighbors]) continue

            dfs(neighbors)

            dp[root][NOT_NICE] += max(dp[neighbors][NICE], dp[neighbors][NOT_NICE])
            dp[root][NICE] += dp[neighbors][NOT_NICE]
        }
    }
}

fun main() {
    val problem = 우수마을_1949()
    problem.solution()
}