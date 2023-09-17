package 다이나믹프로그래밍

class 피보나치는지겨웡_17175 {

    companion object {
        private const val MOD = 1000000007
    }

    fun solution() {
        val n = readln().toInt()
        /*
        n = 3
        fibo(3) -> fibo(2) + fibo(1) + 1
        fibo(2) -> fibo(1) + fibo(0) + 1
        fibo(1) -> return 1
        fibo(0) -> return 1
         */

        val dp = IntArray(51)

        dp[0] = 1
        dp[1] = 1

        for (i in 2..n) {
            dp[i] = (dp[i - 1] + dp[i - 2] + 1) % MOD
        }

        println(dp[n] % MOD)
    }
}

fun main() {
    val sol = 피보나치는지겨웡_17175()
    sol.solution()
}