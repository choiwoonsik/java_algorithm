package 코틀린_코테스터디.week00

import java.math.BigInteger

class Solution1914 {
    companion object {
        private var N: Int = 0
        private val sb = StringBuilder()
        private var count = 0
    }

    fun solution() {
        N = readln().toInt()

        if (N <= 20) {
            hanoi(N, 1, 2, 3)
            print("$count\n$sb")
        } else {
            print(find(N))
        }
    }

    fun find(n: Int): String {
        return (BigInteger.valueOf(2).pow(n) - BigInteger.valueOf(1)).toString()
    }

    fun hanoi(n: Int, from: Int, mid: Int, to: Int) {
        if (n == 1) {
            sb.append("$from $to\n")
            count++
            return
        }

        hanoi(n - 1, from, to, mid)
        sb.append("$from $to\n")
        count++
        hanoi(n - 1, mid, from, to)
    }
}

fun main() {
    Solution1914().solution()
}