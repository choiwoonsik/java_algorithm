package 다이나믹프로그래밍

class 돌게임3_9657 {
    fun solution() {
        val n = readln().toInt()

        val dp = Array(1001) { "" }

        dp[1] = "SK"
        dp[2] = "CY"
        dp[3] = "SK"
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
    val c = 돌게임3_9657()
    c.solution()
}