package 힣_코틀린_코테스터디.week14

import java.util.LinkedList
import kotlin.math.max

class Solution {
    companion object {
        private var N: Int = 0
        private val gateSet: MutableSet<Int> = mutableSetOf()
        private val summitSet: MutableSet<Int> = mutableSetOf()
        private lateinit var dp: IntArray
        private lateinit var adj: Array<MutableList<Node>>
    }

    private data class Node(
        val idx: Int,
        val cost: Int,
    )

    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        N = n
        dp = IntArray(N + 1) { Int.MAX_VALUE }
        adj = Array(N + 1) { mutableListOf() }
        gates.forEach { gateSet.add(it) }
        summits.forEach { summitSet.add(it) }

        for (path in paths) {
            val from = path[0]
            val to = path[1]
            val cost = path[2]
            adj[from].add(Node(to, cost))
            adj[to].add(Node(from, cost))
        }

        for (gate in gates) {
            bfs(gate)
        }

        var summitNumber = 0
        var minCost = Int.MAX_VALUE
        for (summit in summits) {
            if (minCost > dp[summit]) {
                minCost = dp[summit]
                summitNumber = summit
            } else if (minCost == dp[summit] && summitNumber > summit) {
                summitNumber = summit
            }
        }

        return intArrayOf(summitNumber, minCost)
    }

    private fun bfs(gate: Int) {
        val pq = LinkedList<Node>()
        pq.add(Node(gate, 0))

        while (pq.isNotEmpty()) {
            val cur = pq.poll()

            if (summitSet.contains(cur.idx)) continue

            for (next in adj[cur.idx]) {
                if (gateSet.contains(next.idx)) continue

                val maxCost = max(cur.cost, next.cost)
                if (dp[next.idx] > maxCost) {
                    dp[next.idx] = maxCost
                    pq.add(Node(next.idx, maxCost))
                }
            }
        }
    }
}
