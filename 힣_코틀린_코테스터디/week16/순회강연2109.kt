package 힣_코틀린_코테스터디.week16

import java.util.PriorityQueue

/*
7
20 1
2 1
10 3
100 2
8 2
5 20
50 10
 */
class Solution2109 {

    fun solution() {
        val n = readln().toInt()
        val candidate = PriorityQueue<Node>(compareBy { it.day })
        val pq = PriorityQueue<Node>(compareBy { it.pay })

        for (i in 1..n) {
            val line = readln().split(" ")
            val pay = line[0].toInt()
            val day = line[1].toInt()

            val node = Node(day, pay)
            candidate.add(node)
        }

        while (candidate.isNotEmpty()) {
            val can = candidate.poll()

            if (pq.size < can.day) {
                pq.add(can)
            } else {
                if (pq.peek().pay < can.pay) {
                    pq.poll()
                    pq.add(can)
                }
            }
        }

        print(pq.sumOf { it.pay })
    }

    private data class Node(
        val day: Int,
        val pay: Int,
    )
}

fun main() {
    Solution2109().solution()
}