package 다이나믹프로그래밍

import java.lang.StringBuilder

/*
if a <= 0 or b <= 0 or c <= 0, then w(a, b, c) returns:
    1

if a > 20 or b > 20 or c > 20, then w(a, b, c) returns:
    w(20, 20, 20)

if a < b and b < c, then w(a, b, c) returns:
    w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c)

otherwise it returns:
    w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1)
 */

class 신나는함수실행_9148 {
    val dp = Array(21) { Array(21) { IntArray(21) { Int.MAX_VALUE } } }
    val answer = StringBuilder()

    fun solution() {
        dp[0][0][0] = 1

        while (true) {
            val (a, b, c) = readln().split(" ").map { it.toInt() }
            if (a == -1 && b == -1 && c == -1) break
            val num = getNumber(a, b, c)
            answer.append("w($a, $b, $c) = $num\n")
        }
        println(answer)
    }

    private fun getNumber(a: Int, b: Int, c: Int): Int {
        if (a <= 0 || b <= 0 || c <= 0) return dp[0][0][0]
        if (a > 20 || b > 20 || c > 20) return getNumber(20, 20, 20)
        if (dp[a][b][c] != Int.MAX_VALUE) return dp[a][b][c]
        if (a < b && b < c) {
            dp[a][b][c] = getNumber(a, b, c - 1) + getNumber(a, b - 1, c - 1) - getNumber(a, b - 1, c)
            return dp[a][b][c]
        } else {
            dp[a][b][c] = getNumber(a - 1, b, c) + getNumber(a - 1, b - 1, c) + getNumber(a - 1, b, c - 1) - getNumber(a - 1, b - 1, c - 1)
            return dp[a][b][c]
        }
    }
}

fun main() {
    val sol = 신나는함수실행_9148()
    sol.solution()
}