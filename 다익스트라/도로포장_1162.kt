package 다익스트라

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.min

/*
4 4 1
1 2 10
2 4 10
1 3 1
3 4 100
 */

private var N: Int = 0
private var M: Int = 0
private var K: Int = 0
private const val MAX = Long.MAX_VALUE
private lateinit var adj: Array<MutableList<Pair<Int, Int>>>
private lateinit var dp: Array<Array<Long>>
private lateinit var visited: Array<Array<Boolean>>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m, k) = br.readLine().split(" ").map { it.toInt() }
    N = n
    M = m
    K = k

    adj = Array(N + 1) { mutableListOf() }
    dp = Array(N + 1) { Array(K + 1) { MAX } }
    visited = Array(N + 1) { Array(K + 1) { false } }

    repeat(M) {
        val (u, v, cost) = br.readLine().split(" ").map { it.toInt() }
        adj[u].add(v to cost)
        adj[v].add(u to cost)
    }

    dp[1][0] = 0
    dijkstra()
    var answer: Long = dp[N][0]
    for (i in 0..K) {
        answer = min(answer, dp[N][i])
    }

    print(answer)
}

private fun dijkstra() {
    val pq = PriorityQueue<Triple<Int, Long, Int>>(compareBy { it.second })
    pq.add(Triple(1, 0, 0))

    while (pq.isNotEmpty()) {

        val (cur, curCost, useBridge) = pq.poll()

        if (useBridge > K) continue

        if (dp[cur][useBridge] < curCost) continue

        visited[cur][useBridge] = true

        for ((next, nextCost) in adj[cur]) {
            if (!visited[next][useBridge]) {
                if (dp[cur][useBridge] + nextCost < dp[next][useBridge]) {
                    dp[next][useBridge] = dp[cur][useBridge] + nextCost
                    pq.add(Triple(next, dp[next][useBridge], useBridge))
                }
                if (useBridge < K && dp[cur][useBridge] < dp[next][useBridge + 1]) {
                    dp[next][useBridge + 1] = dp[cur][useBridge]
                    pq.add(Triple(next, dp[next][useBridge + 1], useBridge + 1))
                }
            }
        }
    }
}