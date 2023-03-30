package 코틀린_코테스터디.week13

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.min

/*
0 0 0 0 0 0 0 0 0 0
0 1 1 0 0 0 0 0 0 0
0 1 1 1 0 0 0 0 0 0
0 0 1 1 1 1 1 0 0 0
0 0 0 1 1 1 1 0 0 0
0 0 0 0 1 1 1 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
 */
class Problem17136 {
    companion object {
        private const val BLANK = 0
        private const val ONE = 1
        private const val STICKER = 2
    }

    private val map = Array(11) { IntArray(11) }
    private var minCount = Int.MAX_VALUE
    private val pages = mutableMapOf(1 to 5, 2 to 5, 3 to 5, 4 to 5, 5 to 5)

    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))

        for (y in 0 until 10) {
            val st = StringTokenizer(br.readLine())
            for (x in 0 until 10) {
                map[y][x] = st.nextToken().toInt()
            }
        }

        recursive(0, 0, 0)
        if (minCount == Int.MAX_VALUE) print(-1)
        else print(minCount)
    }

    private fun recursive(y: Int, x: Int, pageCount: Int) {
        if (y == 9 && x == 10) {
            minCount = min(minCount, pageCount)
            return
        }

        if (minCount <= pageCount) return

        if (x == 10) {
            recursive(y + 1, 0, pageCount)
            return
        }

        if (map[y][x] == ONE) {
            for (size in 5 downTo 1) {
                if (canCover(y, x, size)) {
                    cover(y, x, size, STICKER)
                    pages[size] = pages[size]!! - 1
                    recursive(y, x + 1, pageCount + 1)
                    cover(y, x, size, ONE)
                    pages[size] = pages[size]!! + 1
                }
            }
        } else recursive(y, x + 1, pageCount)
    }

    private fun canCover(y: Int, x: Int, size: Int): Boolean {
        if (y + size - 1 >= 10) return false
        if (x + size - 1 >= 10) return false
        if (pages[size]!! <= 0) return false

        for (ny in y until y + size) {
            for (nx in x until x + size) {
                if (map[ny][nx] != ONE) return false
            }
        }

        return true
    }

    private fun cover(y: Int, x: Int, size: Int, cov: Int) {
        for (ny in y until y + size) {
            for (nx in x until x + size) {
                map[ny][nx] = cov
            }
        }
    }
}

fun main() {
    Problem17136().solution()
}