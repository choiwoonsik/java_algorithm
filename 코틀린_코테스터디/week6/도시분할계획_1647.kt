package 코틀린_코테스터디.week6

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

var N = 0
var M = 0
private lateinit var p: IntArray
private var pq = PriorityQueue(Comparator.comparingInt { s: Edge -> s.span })

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    p = IntArray(N + 1)
    for (i in 0 until N + 1) p[i] = i
    for (i in 0 until M) {
        st = StringTokenizer(br.readLine())
        val s: Int = st.nextToken().toInt()
        val e: Int = st.nextToken().toInt()
        val span: Int = st.nextToken().toInt()
        pq.add(Edge(s, e, span))
    }
    var selected = 0
    var total_span = 0

    while (selected < N - 2 && !pq.isEmpty()) {
        val edge = pq.poll()

        // check cycle
        if (find(edge.s) != find(edge.e)) {
            union(edge.s, edge.e)
            selected++
            total_span += edge.span
        }
    }
    println(total_span)
}

private fun union(s: Int, e: Int) {
    p[p[s]] = p[e]
}

private fun find(s: Int): Int {
    return if (p[s] == s) s else find(p[s]).also { p[s] = it }
}

private class Edge(
    var s: Int,
    var e: Int,
    var span: Int
)
