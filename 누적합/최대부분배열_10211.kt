package 누적합

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.max
import java.util.StringTokenizer

/*
2
5
1 2 3 4 5
5
2 1 -2 3 -5
 */
private lateinit var dp: Array<Int>
private lateinit var A: Array<Int>
private val sb = StringBuilder()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()

    repeat(N) {
        val M = br.readLine().toInt()
        dp = Array(M) {0}
        A = Array(M) {0}

        val st = StringTokenizer(br.readLine())

        repeat(M) { i ->
            A[i] = st.nextToken().toInt()
        }

        dp[0] = A[0]
        var max = dp[0]
        for (i in 1 until M) {
            dp[i] = max(dp[i - 1] + A[i], A[i])
            max = max(dp[i], max)
        }

        sb.append(max).append("\n")
    }

    print(sb)
}