package 코틀린_코테스터디.week14

import java.util.LinkedList
import kotlin.math.max
import kotlin.math.min

class Solution등산코스정하기 {
    companion object {
        private lateinit var adj: Array<MutableList<Edge>>
        private lateinit var dp: IntArray
        private lateinit var summitSet: MutableSet<Int>
        private lateinit var gateSet: MutableSet<Int>
        private var N: Int = 0
    }

    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        N = n
        adj = Array(N + 1) { mutableListOf() }
        dp = IntArray(N + 1) { Int.MAX_VALUE }

        summitSet = mutableSetOf()
        gateSet = mutableSetOf()

        gates.map { gateSet.add(it) }
        summits.map { summitSet.add(it) }

        for (path in paths) {
            val cur = path[0]
            val next = path[1]
            val cost = path[2]
            adj[cur].add(Edge(next, cost))
            adj[next].add(Edge(cur, cost))
        }

        for (gate in gates) {
            bfs(gate)
        }

        var summitNumber = 0
        var summitMaxCost = Int.MAX_VALUE
        for (cur in summits) {
            if (summitMaxCost >= dp[cur]) {
                if (summitMaxCost == dp[cur]) {
                    summitNumber = min(summitNumber, cur)
                } else {
                    summitMaxCost = dp[cur]
                    summitNumber = cur
                }
            }
        }

        return intArrayOf(summitNumber, summitMaxCost)
    }

    private fun bfs(gate: Int) {
        val queue = LinkedList<Node>()
        queue.add(Node(gate, 0))

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            // 정상 도달 시 더 탐색 안함
            if (summitSet.contains(cur.node)) continue

            for (edge in adj[cur.node]) {
                if (gateSet.contains(edge.next).not()) {
                    val maxCost = max(edge.cost, cur.maxCost)

                    if (dp[edge.next] > maxCost) {
                        dp[edge.next] = maxCost
                        queue.add(Node(edge.next, maxCost))
                    }
                }
            }
        }
    }

    data class Edge(
        val next: Int,
        val cost: Int,
    )

    data class Node(
        val node: Int,
        val maxCost: Int,
    )
}

fun main() {
    val arr = arrayOf(
        intArrayOf(1, 4, 4),
        intArrayOf(1, 6, 1),
        intArrayOf(1, 7, 3),
        intArrayOf(2, 5, 2),
        intArrayOf(3, 7, 4),
        intArrayOf(5, 6, 6)
    )
    Solution등산코스정하기().solution(7, arr, intArrayOf(1), intArrayOf(2, 3, 4))
}