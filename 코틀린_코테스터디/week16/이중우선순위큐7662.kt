package 코틀린_코테스터디.week16

import java.util.PriorityQueue
import java.util.StringTokenizer

class Solution7662 {
    /*
    1. 데이터 사입
    2. 데이터 삭제 - 우선순위 젤 높음
    3. 데이터 삭제 - 우선순위 젤 낮음
     */

    companion object {
        private lateinit var pqAsc: PriorityQueue<Int>
        private lateinit var pqDesc: PriorityQueue<Int>
        private lateinit var map: MutableMap<Int, Int>
        private var N: Int = 0
        private val sb = StringBuilder()
    }

    fun solution() {
        val t = readln().toInt()

        repeat(t) {
            val st = StringTokenizer(readln())
            N = st.nextToken().toInt()
            pqAsc = PriorityQueue()
            pqDesc = PriorityQueue(Comparator.reverseOrder())
            map = mutableMapOf()
            go()
        }

        print(sb)
    }

    private fun go() {
        for (i in 1..N) {
            val st = StringTokenizer(readln())
            val com = st.nextToken()
            val num = st.nextToken().toInt()

            if (com == "I") {
                insert(num)
            } else if (com == "D") {
                if (empty()) continue
                else {
                    if (num == 1) pollMax()
                    if (num == -1) pollMin()
                }
            }
        }

        if (empty()) sb.append("EMPTY")
        else {
            val top: Int
            while (true) {
                val t = pqDesc.poll()
                if (map[t]!! != 0) {
                    top = t
                    break
                }
                else continue
            }

            val bottom: Int
            while (true) {
                val b = pqAsc.poll()
                if (map[b]!! != 0) {
                    bottom = b
                    break
                }
                else continue
            }
            sb.append("$top $bottom")
        }
        sb.append("\n")
    }

    private fun empty(): Boolean {
        return map.values.any { it > 0 }.not()
    }

    private fun pollMin() {
        while (true) {
            if (map[pqAsc.peek()]!! == 0) {
                pqAsc.poll()
                continue
            } else break
        }
        val top = pqAsc.poll()
        map[top] = map[top]!! - 1
    }

    private fun pollMax() {
        while (true) {
            if (map[pqDesc.peek()]!! == 0) {
                pqDesc.poll()
                continue
            } else break
        }
        val top = pqDesc.poll()
        map[top] = map[top]!! - 1
    }

    private fun insert(num: Int) {
        pqAsc.add(num)
        pqDesc.add(num)
        if (map[num] == null) map[num] = 1
        else map[num] = map[num]!! + 1
    }
}

fun main() {
    Solution7662().solution()
}
/*
1
9
I 36
I 37
I 38
D 1
D 1
I 39
I 40
D -1
D -1
 */