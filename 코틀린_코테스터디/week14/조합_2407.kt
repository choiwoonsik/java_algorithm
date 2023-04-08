package 코틀린_코테스터디.week14

import java.math.BigInteger.*

class Solution2407 {

    fun solution() {
        val line = readln().split(" ")
        var n = line[0].toBigInteger()
        var m = line[1].toBigInteger()

        if (n - m < m) m = n - m

        var mother = ONE
        val min = n - m + ONE
        while (true) {
            mother = mother.multiply(n)
            n -= ONE
            if (n < min) break
        }


        var son = ONE
        while (true) {
            son = son.multiply(m)
            m -= ONE
            if (m < ONE) break
        }

        print(mother / son)
    }
}

fun main() {
    Solution2407().solution()
}