import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max


private var T = 0
private var N = 0
private var K = 0
private lateinit var D: IntArray
private lateinit var countDegree: IntArray
private var adj: ArrayList<ArrayList<Int>> = ArrayList()
private var que: Queue<Int> = LinkedList()
private var br = BufferedReader(InputStreamReader(System.`in`))
private var st: StringTokenizer? = null

fun main() {
    st = StringTokenizer(br.readLine())
    val str = StringBuilder()
    T = st!!.nextToken().toInt()

    while (T-- > 0) {
        val time = play()
        str.append(time).append("\n")
    }

    println(str)
}

private fun play(): Int {
    que.clear()
    st = StringTokenizer(br.readLine())
    N = st!!.nextToken().toInt()
    K = st!!.nextToken().toInt()

    //작업 시간
    st = StringTokenizer(br.readLine())
    D = IntArray(N + 1)
    for (n in 1..N) {
        D[n] = st!!.nextToken().toInt()
    }

    // 연관된 작업
    countDegree = IntArray(N + 1)
    adj = ArrayList()
    for (i in 0..N) {
        adj.add(ArrayList())
    }

    // 작업 연결
    for (k in 0 until K) {
        st = StringTokenizer(br.readLine())
        val first = st!!.nextToken().toInt()
        val after = st!!.nextToken().toInt()
        adj[first].add(after)
        countDegree[after]++
    }

    // 마지막 작업
    st = StringTokenizer(br.readLine())
    val w = st!!.nextToken().toInt()
    for (i in 1..N) {
        if (countDegree[i] == 0) {
            que.add(i)
        }
    }

    val timeArr = IntArray(N + 1)
    Arrays.fill(timeArr, 0)

    while (countDegree[w] > 0 && !que.isEmpty()) {
        val job = que.poll() // 먼저 할거
        for (nextJob in adj[job]) { // job에 연결된 다음 작업 실시
            timeArr[nextJob] = max(timeArr[nextJob], timeArr[job] + D[job])
            countDegree[nextJob]--
            if (countDegree[nextJob] == 0) que.add(nextJob)
        }
    }

    timeArr[w] += D[w]
    return timeArr[w]
}
