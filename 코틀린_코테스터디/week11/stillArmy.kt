package 코틀린_코테스터디.week11

import java.util.Comparator
import java.util.LinkedList
import java.util.PriorityQueue

class Solution {
    var N: Int = 0
    lateinit var adj: Array<MutableList<Int>>

    data class Node(
        val next: Int,
        val count: Int,
    )

    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        N = n
        adj = Array(N + 1) { mutableListOf() }
        val result = IntArray(sources.size)
        val record = mutableMapOf<Int, Int>()
        sources.map { record[it] = -1 }

        roads.map {
            val road = it
            val u = road[0]
            val v = road[1]

            adj[u].add(v)
            adj[v].add(u)
        }

        sources.mapIndexed { index, source ->
            val dist = if (record[source] == -1) {
                record[source] = bfs(source, destination)
                record[source]
            } else record[source]
            result[index] = dist!!
        }

        return result
    }

    fun bfs(source: Int, destination: Int): Int {
        val queue = PriorityQueue<Node>(Comparator.comparingInt { node -> -node.count })
        queue.add(Node(source, 0))
        val visited = BooleanArray(N + 1)

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.next == destination) {
                return cur.count
            }

            for (next in adj[cur.next]) {
                if (!visited[next]) {
                    visited[next] = true
                    queue.add(Node(next, cur.count + 1))
                }
            }
        }
        return -1
    }
}

fun main() {
    val sol = Solution()
    val solution = sol.solution(
        3,
        arrayOf(intArrayOf(1, 2), intArrayOf(2, 3)),
        intArrayOf(2, 3),
        1,
    )
    println(solution)
}