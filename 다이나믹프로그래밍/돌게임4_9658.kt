package 다이나믹프로그래밍

class 돌게임4_9658 {
    fun solution() {
        val n = readln().toInt()
        val dp = Array(1001) { "" }

        dp[1] = "CY"
        dp[2] = "SK"
        dp[3] = "CY"
        dp[4] = "SK"

        for (i in 5..n) {
            if (dp[i - 1] == "CY" || dp[i - 3] == "CY" || dp[i - 4] == "CY")
                dp[i] = "SK"
            else dp[i] = "CY"
        }

        println(dp[n])
    }
}

fun main() {
    돌게임4_9658().solution()
}