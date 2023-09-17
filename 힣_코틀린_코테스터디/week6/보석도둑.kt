import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/*
3 2
1 65
5 23
2 99
10
2
*/

private var N = 0
private var K = 0
private var jeweryQ = PriorityQueue(Comparator.comparing { J: Jew -> J.weigh })
private var jQ = PriorityQueue(Comparator.comparingInt { J: Jew -> -J.price })
private var bagQ = PriorityQueue<Int>()


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    K = st.nextToken().toInt()
    for (i in 0 until N) {
        st = StringTokenizer(br.readLine())
        val w = st.nextToken().toInt()
        val p = st.nextToken().toInt()
        jeweryQ.add(Jew(w, p))
    }
    for (i in 0 until K) {
        bagQ.add(br.readLine().toInt())
    }
    var total: Long = 0
    while (!bagQ.isEmpty()) {
        val bag = bagQ.poll()
        while (!jeweryQ.isEmpty()) {
            if (jeweryQ.peek().weigh <= bag) {
                jQ.add(jeweryQ.poll())
            } else break
        }
        if (!jQ.isEmpty()) total += jQ.poll().price.toLong()
    }
    println(total)
}

private class Jew(var weigh: Int, var price: Int)
