package 코틀린_코테스터디.week18

/*
3 5 10
5 3 7
 */
class Solution1495 {

    companion object {
        private var N: Int = 0
        private var S: Int = 0
        private var M: Int = 0
        private lateinit var vol: IntArray
        private lateinit var dp: Array<IntArray> // n일때 최대로
    }

    fun solution() {
        val l = readln().split(" ")
        N = l[0].toInt()
        S = l[1].toInt()
        M = l[2].toInt()
        vol = readln().split(" ").map { it.toInt() }.toIntArray()

        dp = Array(N + 1) { IntArray(M + 1) { -1 } }
        dp[0][S] = S

        for (song in 1..N) {
            for (v in 0..M) {
                if (dp[song - 1][v] != -1) {
                    val nowVol = dp[song - 1][v]
                    val diff = vol[song - 1]
                    val plus = nowVol + diff
                    val minus = nowVol - diff
                    if (plus <= M) dp[song][plus] = nowVol + diff
                    if (minus >= 0) dp[song][minus] = nowVol - diff
                }
            }
        }

        for (v in M downTo 0) {
            if (dp[N][v] != -1) {
                print(dp[N][v])
                return
            }
        }
        print(-1)
    }
}

fun main() {
    Solution1495().solution()
}