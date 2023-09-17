package 힣_코틀린_코테스터디.week8

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max


class 양구출작전_16437 {
    private var N = 0
    private lateinit var adj: Array<MutableList<Node>>
    private lateinit var dp: Array<Long>

    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st: StringTokenizer

        N = br.readLine().toInt()
        adj = Array(N + 1) { (mutableListOf()) }
        dp = Array(N + 1) { 0L }

        for (i in 0 until N + 1) {
            adj[i] = mutableListOf()
        }

        for (u in 2..N) {
            st = StringTokenizer(br.readLine())
            val type = st.nextToken()
            val count = st.nextToken().toInt()
            val v = st.nextToken().toInt()
            if (type == "W") {
                adj[v].add(Node(u, -count))
            } else {
                adj[v].add(Node(u, count))
            }
        }
        dfs(1)
        println(dp[1])
    }

    private fun dfs(node: Int) {
        for (child in adj[node]) {
            dp[child.here] = child.count.toLong()
            dfs(child.here)
            dp[node] += max(dp[child.here], 0)
        }
    }

    private class Node(
        var here: Int,
        var count: Int
    )
}

fun main() {
    val problem = 양구출작전_16437()
    problem.solution()
}