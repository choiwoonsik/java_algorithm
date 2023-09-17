package 힣_코틀린_코테스터디.week8

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.StringTokenizer

/*
5 7 1
1 2 5
3 1 4
2 4 8
3 2 3
5 2 9
3 4 7
4 5 6
 */

class 인터넷_설치_1800 {
    private var N = 0
    private var M = 0
    private var K = 0
    private lateinit var adj: Array<MutableList<Node>>
    private lateinit var dp: Array<Int>
    private lateinit var trace: Array<MutableList<Int>>
    private lateinit var costArr: Array<Array<Int>>

    data class Node(
        val to: Int,
        val cost: Int,
    )

    fun solution() {
        var br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        N = st.nextToken().toInt()
        M = st.nextToken().toInt()
        K = st.nextToken().toInt()

        adj = Array(N + 1) { mutableListOf() }
        trace = Array(N + 1) { mutableListOf() }
        dp = Array(N + 1) { Int.MAX_VALUE - 1 }
        costArr = Array(N + 1) { Array(N + 1) { Int.MAX_VALUE - 1 } }

        repeat(N) {
            st = StringTokenizer(br.readLine())
            val from = st.nextToken().toInt()
            val to = st.nextToken().toInt()
            val cost = st.nextToken().toInt()
            adj[from].add(Node(to, cost))
        }

        dijkstra()
        if (dp[N] == Int.MAX_VALUE - 1) {
            println(-1)
        } else {
            trace()
        }
    }

    private fun trace() {
        val pq = PriorityQueue<Int>(compareBy { -it })
        val queue = LinkedList<Int>()
        queue.add(N)

        while (queue.isNotEmpty()) {
            val now = queue.poll()
            val before = trace[now].first()

            pq.add(costArr[before][now])
            queue.add(before)
        }

        queue.poll()
        println(queue.poll())
    }

    private fun dijkstra() {
        val queue = LinkedList<Node>()
        queue.add(Node(1, 0))

        while (queue.isNotEmpty()) {
            val (from, curCost) = queue.poll()

            if (dp[from] < curCost) continue

            for (next in adj[from]) {
                val nextTo = next.to
                val nextCost = next.cost

                if (dp[nextTo] > dp[from] + nextCost) {
                    dp[nextTo] = dp[from] + nextCost
                    trace[nextTo].clear()
                    trace[nextTo].add(from)
                }
            }
        }
    }
}

fun main() {
    val problem = 인터넷_설치_1800()
    problem.solution()
}