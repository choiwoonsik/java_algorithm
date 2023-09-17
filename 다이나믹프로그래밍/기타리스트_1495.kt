package 다이나믹프로그래밍

class 기타리스트_1495 {
    fun solution() {
        val (n, s, m) = readln().split(" ").map { it.toInt() }
        val vArr = readln().split(" ").map { it.toInt() }.toIntArray()

        val dp = Array(51) { IntArray(1001) { -1 } }
        dp[0][s] = s

        for (i in 1..n) {
            var isChecked = false
            for (p in 0..m) {
                if (dp[i - 1][p] != -1) {
                    val v = vArr[i - 1]
                    if (p + v <= m) {
                        dp[i][p + v] = p + v
                        isChecked = true
                    }
                    if (p - v >= 0) {
                        dp[i][p - v] = p - v
                        isChecked = true
                    }
                }
            }
            if (isChecked.not()) {
                println(-1)
                return
            }
        }

        println(dp[n].maxOf { it })
    }
}

fun main() {
    기타리스트_1495().solution()
}