package 코틀린_코테스터디.week12

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


class Problem1655 {
    private var N = 0
    private var minHeap = PriorityQueue<Int>()
    private var maxHeap = PriorityQueue(Comparator.reverseOrder<Int>())
    private var answer = StringBuilder()

    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val n = br.readLine().toInt()
        repeat(n) { findMid(br.readLine().toInt()) }
        println(answer)
    }

    private fun findMid(input: Int) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(input)
        } else {
            if (maxHeap.size == minHeap.size) {
                if (input <= maxHeap.peek())
                    maxHeap.add(input)
                else {
                    minHeap.add(input)
                    maxHeap.add(minHeap.poll())
                }
            } else if (maxHeap.size > minHeap.size) {
                if (input <= maxHeap.peek()) {
                    maxHeap.add(input)
                    minHeap.add(maxHeap.poll())
                } else
                    minHeap.add(input)
            }
        }
        answer.append(maxHeap.peek()).append("\n")
    }
}

fun main() {
    Problem1655().solution()
}