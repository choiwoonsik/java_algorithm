package 코틀린_코테스터디.week14

import java.util.StringTokenizer
import kotlin.math.pow

class Solution1629 {

    fun solution() {
        val st = StringTokenizer(readln())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()

        print(recursive(a, b, c))
    }

    private fun recursive(a: Int, b: Int, c: Int): Int {
        return if (b <= 1) {
            a % c
        } else {
            if (b % 2 == 0) {
                val tmp = recursive(a, b / 2, c)
                (tmp * tmp) % c
            } else {
                val tmp = recursive(a, b / 2, c)
                (tmp * ((tmp * (a % c)) % c)) % c
            }
        }
    }
}

fun main() {
    Solution1629().solution()
}