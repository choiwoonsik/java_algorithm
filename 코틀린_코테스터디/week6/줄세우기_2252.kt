import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var N = 0
private var M: Int = 0
private lateinit var adj: Array<MutableList<Int>>
private lateinit var degree: IntArray
private var degree_queue: Queue<Int> = LinkedList()
private var str = StringBuilder()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    degree = IntArray(N + 1)
    adj = Array(N + 1) { mutableListOf() }
    for (i in 0 until N + 1) {
        adj[i] = mutableListOf()
    }
    for (m in 0 until M) {
        st = StringTokenizer(br.readLine())
        val front = st.nextToken().toInt()
        val back = st.nextToken().toInt()
        adj[front].add(back)
        degree[back]++
    }
    for (i in 1 until N + 1) {
        if (degree[i] == 0) {
            str.append(i).append(" ")
            degree_queue.add(i)
        }
    }
    while (!degree_queue.isEmpty()) {
        val now = degree_queue.poll()
        for (next in adj[now]) {
            degree[next]--
            if (degree[next] == 0) {
                degree_queue.add(next)
                str.append(next).append(" ")
            }
        }
    }
    println(str)
}