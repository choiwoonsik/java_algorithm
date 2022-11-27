package 다익스트라

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class 비밀모임_13424

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
private var adj = Array(0) { mutableListOf<Pair<Int, Int>>() }
private var dp = IntArray(0) { 0 }
private var INF = (Int.MAX_VALUE - 1) / 2

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    N = br.readLine().toInt()
    M = br.readLine().toInt()
    adj = Array(N + 1) { mutableListOf() }

    repeat(M) {
        val st = StringTokenizer(br.readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        adj[u].add(v to c)
    }

    val st = StringTokenizer(br.readLine())
    val s = st.nextToken().toInt()
    val e = st.nextToken().toInt()

    dp = IntArray(N + 1) { INF }
    dp[s] = 0
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    pq.add(s to 0)

    while (pq.isNotEmpty()) {
        val (cur, curDist) = pq.poll()

        if (dp[cur] < curDist) continue
        for ((next, nextDist) in adj[cur]) {
            if (dp[next] > dp[cur] + nextDist) {
                dp[next] = dp[cur] + nextDist
                pq.add(next to dp[next])
            }
        }
    }

    print(dp[e])
}