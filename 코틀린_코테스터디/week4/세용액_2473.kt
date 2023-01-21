import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs

/*
7
-2 -3 -24 -6 98 100 61

-24 -6 -3 -2 61 98 100

-97 -6 -2 6 98
 */

private lateinit var liquids: Array<Long>
private lateinit var subLiquids: Array<Long>
private var N = 0
private var MIN = Long.MAX_VALUE
private var abc: Triple<Long, Long, Long> = Triple(0, 0, 0)
private var noMore: Boolean = false

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    N = br.readLine().toInt()
    liquids = Array(N) { 0 }

    val st = StringTokenizer(br.readLine())
    repeat(N) { i ->
        liquids[i] = st.nextToken().toLong()
    }

    Arrays.sort(liquids)

    for (index in 0 until N) {
        subLiquids = liquids.copyOfRange(index + 1, N)
        binarySearch(0, subLiquids.size - 1, liquids[index])
        if (noMore) {
            print("${abc.first} ${abc.second} ${abc.third}")
            return
        }
    }
    print("${abc.first} ${abc.second} ${abc.third}")
}

fun binarySearch(start: Int, end: Int, key: Long) {
    var sum: Long = 0
    var start = start
    var end = end

    while (start < end) {
        sum = subLiquids[start] + subLiquids[end] + key

        if (sum > 0) {
            if (abs(sum) < MIN) {
                MIN = abs(sum)
                abc = Triple(key, subLiquids[start], subLiquids[end])
            }
            end--
        }
        else if (sum < 0) {
            if (abs(sum) < MIN) {
                MIN = abs(sum)
                abc = Triple(key, subLiquids[start], subLiquids[end])
            }
            start++
        }
        else {
            noMore = true
            abc = Triple(key, subLiquids[start], subLiquids[end])
            break
        }
    }
}
