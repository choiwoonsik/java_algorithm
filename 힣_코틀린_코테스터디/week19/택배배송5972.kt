package 힣_코틀린_코테스터디.week19

import java.util.LinkedList
import java.util.StringTokenizer

/*
6 8
4 5 3
2 4 0
4 1 4
2 1 1
5 6 1
3 6 2
3 2 6
3 4 4
 */
class Solution5972 {
    companion object {
        private var N: Int = 0
        private var M: Int = 8
        private lateinit var adj: Array<MutableList<Node>>
        private lateinit var dp: IntArray
    }

    private data class Node(
        val to: Int,
        val cost: Int,
    )

    fun solution() {
        val l = readln().split(" ")
        N = l[0].toInt()
        M = l[1].toInt()
        adj = Array(N + 1) { mutableListOf() }
        dp = IntArray(N + 1) { Int.MAX_VALUE }

        for (edge in 1..M) {
            val st = StringTokenizer(readln())
            val from = st.nextToken().toInt()
            val to = st.nextToken().toInt()
            val cost = st.nextToken().toInt()
            adj[from].add(Node(to, cost))
            adj[to].add(Node(from, cost))
        }

        dijkstra()
        print(dp[N])
    }

    private fun dijkstra() {
        val queue = LinkedList<Int>()
        queue.add(1)
        dp[1] = 0

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (next in adj[cur]) {
                if (dp[next.to] > dp[cur] + next.cost) {
                    dp[next.to] = dp[cur] + next.cost
                    queue.add(next.to)
                }
            }
        }
    }
}

fun main() {
    Solution5972().solution()
}
