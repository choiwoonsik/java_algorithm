package 누적합

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
5 3
5 4 3 2 1
1 3
2 4
5 5
 */

private var N: Int = 0
private var M: Int = 0
private lateinit var A: Array<Int>
private lateinit var dp: Array<Int>
private val sb = StringBuilder()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    N = n
    M = m
    A = Array(N + 1) { 0 }
    dp = Array(N + 1) { 0 }

    val st = StringTokenizer(br.readLine())
    repeat(N) { idx ->
        val i = idx + 1
        A[i] = st.nextToken().toInt()
        if (i == 0) dp[i] = A[i]
        else dp[i] = dp[i - 1] + A[i]
    }

    repeat(M) {
        val st = StringTokenizer(br.readLine())
        val s = st.nextToken().toInt()
        val e = st.nextToken().toInt()

        sb.append("${dp[e] - dp[s - 1]}\n")
    }

    print(sb)
}