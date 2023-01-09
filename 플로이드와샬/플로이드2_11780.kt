package 플로이드와샬

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer

/*
5
14
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
3 5 10
3 1 8
1 4 2
5 1 7
3 4 2
5 2 4
 */

private val INF = 987654321
private var N: Int = 0
private var M: Int = 0
private lateinit var dp: Array<IntArray>
private lateinit var transfer: Array<Array<MutableList<Int>>>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    N = br.readLine().toInt()
    M = br.readLine().toInt()

    dp = Array(N + 1) { IntArray(N + 1) { INF } }
    transfer = Array(N + 1) { Array(N + 1) { mutableListOf() } }

    repeat(M) { i ->
        val st = StringTokenizer(br.readLine())
        val y = st.nextToken().toInt()
        val x = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        dp[y][x] = c
        if (i <= N) dp[i][i] = 0
    }

    floydWarshall()
    result()
}

private fun result() {
    val sb = StringBuilder()

    repeat(N) { y ->
        repeat(N) { x ->
            sb.append(dp[y + 1][x + 1]).append(" ")
        }
        sb.append("\n")
    }

    repeat(N) { i ->
        repeat(N) { j ->
            val y = i + 1
            val x = j + 1
            if (y == x) sb.append("0")
            else {
                sb.append(transfer[y][x].count() + 2).append(" ")
                sb.append(y).append(" ")
                for (t in transfer[y][x]) sb.append(t).append(" ")
                sb.append(x).append(" ")
            }
            sb.append("\n")
        }
    }

    print(sb)
}

private fun floydWarshall() {
    for (z in 1..N) {
        for (y in 1..N) {
            for (x in 1..N) {
                if (y == x) continue
                if (dp[y][x] > dp[y][z] + dp[z][x]) {
                    dp[y][x] = dp[y][z] + dp[z][x]
                    transfer[y][x].clear()
                    transfer[y][x].addAll(transfer[y][z])
                    transfer[y][x].add(z)
                    transfer[y][x].addAll(transfer[z][x])
                }
            }
        }
    }
}