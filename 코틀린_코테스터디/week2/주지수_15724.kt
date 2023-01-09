package 코틀린_코테스터디.week2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
4 4
9 14 29 7
1 31 6 13
21 26 40 16
8 38 11 23
3
1 1 3 2
1 1 1 4
1 1 4 4
 */

private var N: Int = 0
private var M: Int = 0
private lateinit var origin: Array<Array<Int>>
private lateinit var dp: Array<Array<Int>>
private val br = BufferedReader(InputStreamReader(System.`in`))

fun main() {
    val st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()

    origin = Array(N + 2) { Array(M + 1) { 0 } }
    dp = Array(N + 2) { Array(M + 1) { 0 } }

    repeat(N) { i ->
        val st = StringTokenizer(br.readLine())
        repeat(M) { j ->
            origin[i + 1][j + 1] = st.nextToken().toInt()
        }
    }
    initDP()
    calc()
}

private fun calc() {
    val k = br.readLine().toInt()
    val sb = StringBuilder()

    repeat(k) {
        val st = StringTokenizer(br.readLine())
        val y1 = st.nextToken().toInt()
        val x1 = st.nextToken().toInt()
        val y2 = st.nextToken().toInt()
        val x2 = st.nextToken().toInt()

        var area = dp[y2][x2] - dp[y1 - 1][x2] - dp[y2][x1 - 1] + dp[y1 - 1][x1 - 1]
        sb.append("$area\n")
    }
    print(sb)
}

private fun initDP() {
    dp[1][1] = origin[1][1]
    for (i in 1..N) {
        for (j in 1..M) {
            if (i == 1) {
                if (j == 1) continue
                dp[i][j] = dp[i][j - 1] + origin[i][j]
            } else if (j == 1) {
                dp[i][j] = dp[i - 1][j] + origin[i][j]
            } else {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + origin[i][j]
            }
        }
    }
}
