import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var N = 0
private var M:Int = 0
private var pq = PriorityQueue<Int>()
private lateinit var degree: IntArray
private lateinit var adj: Array<MutableList<Int>>
private var str = StringBuilder()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    adj = Array(N + 1) { mutableListOf() }
    degree = IntArray(N + 1)
    for (i in 0 until N + 1) {
        adj[i] = ArrayList()
    }
    for (i in 0 until M) {
        st = StringTokenizer(br.readLine())
        val first = st.nextToken().toInt()
        val next = st.nextToken().toInt()
        adj[first].add(next)
        degree[next]++
    }
    for (i in 1 until N + 1) {
        if (degree[i] == 0) {
            pq.add(i)
        }
    }
    while (!pq.isEmpty()) {
        val now = pq.poll()
        str.append(now).append(" ")
        for (next in adj[now]) {
            degree[next]--
            if (degree[next] == 0) {
                pq.add(next)
            }
        }
    }
    println(str)
}