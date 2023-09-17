package 다이나믹프로그래밍

class 동물원_1309 {
    companion object {
        val B = 0
        val L = 1
        val R = 2
        val MOD = 9901
    }

    fun solution() {
        val n = readln().toInt()
        val dp = Array(100001) { IntArray(3) }

        dp[1][B] = 1
        dp[1][L] = 1
        dp[1][R] = 1

        for (i in 2..n) {
            dp[i][B] = (dp[i - 1][B] + dp[i - 1][L] + dp[i - 1][R]) % MOD
            dp[i][L] = (dp[i - 1][B] + dp[i - 1][R]) % MOD
            dp[i][R] = (dp[i - 1][B] + dp[i - 1][L]) % MOD
        }

        println((dp[n][B] + dp[n][L] + dp[n][R]) % MOD)
    }
}

fun main() {
    동물원_1309().solution()
}