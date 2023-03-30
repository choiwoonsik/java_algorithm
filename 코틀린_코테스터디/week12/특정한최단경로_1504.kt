package 코틀린_코테스터디.week12

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

class 특정한최단경로_1504 {
    private var N = 0
    private var E = 0
    private var A = 0
    private var B = 0
    private lateinit var distance: LongArray
    private lateinit var adj: Array<ArrayList<Dot>>

    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())
        N = st.nextToken().toInt()
        E = st.nextToken().toInt()
        adj = Array(N + 1) { arrayListOf() }
        for (edge in 0 until E) {
            st = StringTokenizer(br.readLine())
            val from = st.nextToken().toInt()
            val to = st.nextToken().toInt()
            val cost = st.nextToken().toInt()
            adj[from].add(Dot(to, cost.toLong()))
            adj[to].add(Dot(from, cost.toLong()))
        }
        st = StringTokenizer(br.readLine())
        A = st.nextToken().toInt()
        B = st.nextToken().toInt()

        val costOneToA = dijkstra(1, A)
        val costOneToB = dijkstra(1, B)
        val costAtoB = dijkstra(A, B)
        val costAtoN = dijkstra(A, N)
        val costBtoN = dijkstra(B, N)
        var hasCostOneToAtoBtoN = true
        var hasCostOneToBtoAtoN = true

        if (costOneToA == Long.MAX_VALUE || costAtoB == Long.MAX_VALUE || costBtoN == Long.MAX_VALUE)
            hasCostOneToAtoBtoN = false
        if (costOneToB == Long.MAX_VALUE || costAtoB == Long.MAX_VALUE || costAtoN == Long.MAX_VALUE)
            hasCostOneToBtoAtoN = false

        val ret =
            if (!hasCostOneToAtoBtoN && !hasCostOneToBtoAtoN) -1
            else min(costOneToA + costAtoB + costBtoN, costOneToB + costAtoB + costAtoN)
        print(ret)
    }

    private fun dijkstra(start: Int, destination: Int): Long {
        distance = LongArray(N + 1)
        Arrays.fill(distance, Long.MAX_VALUE)

        val pq = PriorityQueue(Comparator.comparingLong { s: Dot -> s.cost })
        pq.add(Dot(start, 0))
        distance[start] = 0

        while (pq.isNotEmpty()) {
            val now = pq.poll()
            for (next in adj[now.dest]) {
                if (distance[next.dest] > distance[now.dest] + next.cost) {
                    distance[next.dest] = distance[now.dest] + next.cost
                    pq.add(next)
                }
            }
        }
        return distance[destination]
    }

    private data class Dot(
        var dest: Int,
        var cost: Long
    )
}

fun main() {
    특정한최단경로_1504().solution()
}
