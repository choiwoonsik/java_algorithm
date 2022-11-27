package 다익스트라

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Arrays
import java.util.PriorityQueue
import java.util.StringTokenizer
import kotlin.math.max

/*
4 8 2
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3
 */

private var N = 0
private var M = 0
private var X = 0
private const val MAX = 987654321
private var adj = Array(N + 1) { mutableListOf<Vertex>() }
private var dp = IntArray(N + 1) { MAX }
private var visited = Array(N + 1) { false }

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    X = st.nextToken().toInt()

    adj = Array(N + 1) { mutableListOf() }
    dp = IntArray(N + 1) { MAX }
    visited = Array(N + 1) { false }

    repeat(M) {
        st = StringTokenizer(br.readLine())
        val from = st.nextToken().toInt()
        val to = st.nextToken().toInt()
        val cost = st.nextToken().toInt()
        adj[from].add(Vertex(to, cost))
    }

    var maxTime = 0
    for (now in 1..N) {
        dijkstra(now)
        val goTime = dp[X]
        dijkstra(X)
        val backTime = dp[now]
        maxTime = max(maxTime, goTime + backTime)
    }
    print(maxTime)
}

private fun dijkstra(start: Int) {
    dp.fill(MAX)
    visited.fill(false)
    val pq = PriorityQueue<Vertex>(compareBy { it.cost })
    pq.add(Vertex(start, 0))
    dp[start] = 0

    while (pq.isNotEmpty()) {
        val (now, cost) = pq.poll()

        if (dp[now] < cost) continue

        visited[now] = true

        for (next in adj[now]) {
            if (!visited[next.to] && dp[now] + next.cost < dp[next.to]) {
                dp[next.to] = dp[now] + next.cost
                pq.add(Vertex(next.to, dp[next.to]))
            }
        }
    }
}

private data class Vertex(
    val to: Int,
    val cost: Int,
)