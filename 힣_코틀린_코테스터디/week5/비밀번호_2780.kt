import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

/*
1 2 3
4 5 6
7 8 9
0

1 -> 2, 4
2 -> 1, 3, 5
3 -> 2, 6
4 -> 1, 5, 7
5 -> 2, 4, 6, 8
6 -> 3, 5, 9
7 -> 4, 8, 0
8 -> 7, 5, 9
9 -> 8, 6
0 -> 7

dp[2][1] = dp[1][2] + dp[1][4]
dp[3][1] = dp[2][2] + dp[2][4] = (dp[1][1] + dp[1][3] + dp[1][5]) + ...
 */

private lateinit var map: Array<Array<Long>>
private lateinit var dp: Array<Array<Long>>
private var T: Int = 0
private var N: Int = 0
private var br: BufferedReader? = null
private var sb = StringBuilder()

fun main() {
    br = BufferedReader(InputStreamReader(System.`in`))
    T = br!!.readLine().toInt()
    dp = Array(1001) { Array(10) { 1 } }

    initDp()
    repeat(T) {
        N = br!!.readLine().toInt()
        val sum = dp[N].sum() % 1234567
        sb.append("$sum\n")
    }
    print(sb)
}

fun initDp() {
    for (t in 2..1000) {
        for (n in 1..10) {
            when (n % 10) {
                1 -> dp[t][1] = dp[t - 1][2] + dp[t - 1][4]
                2 -> dp[t][2] = dp[t - 1][1] + dp[t - 1][3] + dp[t - 1][5]
                3 -> dp[t][3] = dp[t - 1][2] + dp[t - 1][6]
                4 -> dp[t][4] = dp[t - 1][1] + dp[t - 1][5] + dp[t - 1][7]
                5 -> dp[t][5] = dp[t - 1][2] + dp[t - 1][4] + dp[t - 1][6] + dp[t - 1][8]
                6 -> dp[t][6] = dp[t - 1][3] + dp[t - 1][5] + dp[t - 1][9]
                7 -> dp[t][7] = dp[t - 1][4] + dp[t - 1][8] + dp[t - 1][0]
                8 -> dp[t][8] = dp[t - 1][7] + dp[t - 1][5] + dp[t - 1][9]
                9 -> dp[t][9] = dp[t - 1][8] + dp[t - 1][6]
                0 -> dp[t][0] = dp[t - 1][7]
            }
            dp[t][n % 10] = dp[t][n % 10] % 1234567
        }
    }
}
