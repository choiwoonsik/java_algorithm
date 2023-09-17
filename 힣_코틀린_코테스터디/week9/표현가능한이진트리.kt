package 힣_코틀린_코테스터디.week9

import java.util.LinkedList

class 표현가능한이진트리 {
    private var wrong = false

    fun solution(numbers: LongArray): IntArray {
        val answer = mutableListOf<Int>()

        for (num in numbers) {
            val binaryNumber = toBinaryNumber(num)
            answer.add(checkPossibleTree(binaryNumber))
        }
        return answer.toIntArray()
    }

    private fun checkPossibleTree(binaryNumber: String): Int {
        var s = ""
        s = if (binaryNumber.length % 2 == 0) {
            "0$binaryNumber"
        } else binaryNumber

        recursive(s.toCharArray())

        return when (wrong) {
            true -> 0
            false -> 1
        }
    }

    private fun recursive(cur: CharArray) {
        val mid = cur.size / 2

        if (cur.size < 3) return

        if (cur[mid] == '0') {
            wrong = true
        } else {
            recursive(cur.copyOfRange(0, mid))
            recursive(cur.copyOfRange(mid, cur.size))
        }
    }

    private fun toBinaryNumber(num: Long): String {
        val q = LinkedList<Int>(mutableListOf())
        var cur = num
        while (cur > 0) {
            if (cur % 2 == 0L) {
                q.addFirst(0)
                cur = cur.div(2)
            } else {
                val next = cur / 2
                cur = next
                q.addFirst(1)
            }
        }

        val s = StringBuilder()
        while (q.isNotEmpty()) {
            s.append(q.poll())
        }
        return s.toString()
    }
}

fun main() {
    val sol = 표현가능한이진트리()
    val solution = sol.solution(longArrayOf(63, 111, 95))
    solution.map { println(it) }
}
