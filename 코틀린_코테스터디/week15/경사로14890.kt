package 코틀린_코테스터디.week15

import java.util.StringTokenizer
import kotlin.math.abs

/*
6 2
3 3 3 3 3 3
2 3 3 3 3 3
2 2 2 3 2 3
1 1 1 2 2 2
1 1 1 3 3 1
1 1 2 3 3 2
 */

class Solution14890 {
    companion object {
        private var N = 0
        private var L = 0
        private lateinit var map: Array<IntArray>
        private lateinit var block: BooleanArray
    }

    fun solution() {
        val st = StringTokenizer(readln())
        N = st.nextToken().toInt()
        L = st.nextToken().toInt()
        map = Array(N + 1) { IntArray(N + 1) }

        for (i in 0 until N) {
            val line = StringTokenizer(readln())
            for (j in 0 until N) {
                map[i][j] = line.nextToken().toInt()
            }
        }

        var total = 0
        total += countRight()
        total += countDown()

        print(total)
    }

    private fun countRight(): Int {
        var count = 0
        for (row in 0 until N) {
            val line = map[row]
            if (lineCheck(line)) {
                count++
            }
        }

        return count
    }

    private fun countDown(): Int {
        var count = 0
        for (col in 0 until N) {
            val line = mutableListOf<Int>()
            for (row in 0 until N) {
                line.add(map[row][col])
            }
            if (lineCheck(line.toIntArray())) {
                count++
            }
        }

        return count
    }

    private fun lineCheck(line: IntArray): Boolean {
        var col = 0
        var lastHeight: Int
        block = BooleanArray(N + 1)

        while (true) {
            if (col >= N) return true

            lastHeight = line[col]
            var tmpLen = 0

            // 같은 칸 개수
            while (lastHeight == line[col]) {
                if (!block[col]) tmpLen++
                col++
                if (col >= N) return true
            }

            if (abs(lastHeight - line[col]) > 1) return false

            // 내리막길
            if (lastHeight > line[col]) {
                val bottomLand = countRightSameHeight(line, col)
                if (bottomLand < L) return false
                for (i in col until col + L) {
                    block[i] = true
                }
                col = col + L - 1
            }
            // 오르막길
            else {
                if (tmpLen < L) return false
                if (block[col - 1]) return false
            }
        }
    }

    private fun countRightSameHeight(line: IntArray, col: Int): Int {
        val startHeight = line[col]
        var count = 0
        for (i in col until N) {
            if (line[i] == startHeight) count++
            else break
        }
        return count
    }
}

fun main() {
    Solution14890().solution()
}
