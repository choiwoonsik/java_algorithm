package 코틀린_코테스터디.week1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.PriorityQueue
import java.util.StringTokenizer

/*
3 3
1 2
1 3
2 3
 */

private var N: Int = 0
private var M: Int = 0
private lateinit var adj: Array<MutableList<Int>>
private lateinit var colors: Array<Int>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()

    adj = Array(N + 2) { mutableListOf() }
    colors = Array(N + 2) { 0 }

    repeat(M) {
        st = StringTokenizer(br.readLine())
        val bA = st.nextToken().toInt()
        val bB = st.nextToken().toInt()
        val pair = if (bA < bB) bA to bB else bB to bA
        adj[pair.second].add(pair.first)
    }

    for (idx in 1 .. N) {
        val pq = PriorityQueue<Int>()

        for (next in adj[idx]) {
            if (colors[next] != 0 && pq.contains(colors[next])) {
                pq.add(colors[next])
            }
        }

        var myColor = 1
        while (pq.isNotEmpty()) {
            if (pq.peek() == myColor) {
                myColor++
                pq.poll()
            } else break
        }

        colors[idx] = myColor
    }

    val sb = StringBuilder()
    repeat(N) { i ->
        sb.append("${colors[i + 1]} ")
    }
    print(sb)
}
