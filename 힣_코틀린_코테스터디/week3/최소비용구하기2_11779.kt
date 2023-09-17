import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer
import kotlin.text.StringBuilder

/*
5
8
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
1 5
 */

private var N: Int = 0
private var M: Int = 0
private lateinit var adj: Array<MutableList<Node>>
private lateinit var dp: Array<Long>
private lateinit var routeDp: Array<StringBuilder>
private data class Node(
    val node: Int,
    val cost: Int,
)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    N = br.readLine().toInt()
    M = br.readLine().toInt()

    adj = Array(N + 1) { mutableListOf() }
    dp = Array(N + 1) { Long.MAX_VALUE }
    routeDp = Array(N + 1) { StringBuilder() }

    repeat(M) {
        val st = StringTokenizer(br.readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        adj[u].add(Node(v, c))
    }

    val (s, e) = br.readLine().split(" ").map { it.toInt() }
    dijkstra(s, e)
}

private fun dijkstra(start: Int, end: Int) {
    val answer = StringBuilder()
    val queue = PriorityQueue<Pair<Int, Long>>(compareBy { it.second} )
    queue.add(start to 0)
    dp[start] = 0
    routeDp[start] = StringBuilder("$start ")

    while (queue.isNotEmpty()) {
        val (cur, totalCost) = queue.poll()

        if (dp[cur] < totalCost) continue

        if (cur == end) {
            val routeCount = StringTokenizer(routeDp[end].toString()).countTokens()
            answer
                .append("${dp[end]}\n")
                .append("$routeCount\n")
                .append(routeDp[end])
            print(answer)
            break
        }

        for ((next, cost) in adj[cur]) {
            if (dp[next] > dp[cur] + cost) {
                dp[next] = dp[cur] + cost
                routeDp[next] = StringBuilder(routeDp[cur])
                routeDp[next].append("$next ")
                queue.add(next to dp[next])
            }
        }
    }
}
