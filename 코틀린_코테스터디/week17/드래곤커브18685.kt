package 코틀린_코테스터디.week17

import java.util.LinkedList
import java.util.Stack
import java.util.StringTokenizer

/*
4
3 3 0 1
4 2 1 3
4 2 2 1
2 7 3 4
 */
class Solution18685 {

    companion object {
        private const val L = 101
        private var N: Int = 0
        private lateinit var history: LinkedList<Dragon>
        private lateinit var stack: Stack<Dragon>
        private lateinit var map: Array<BooleanArray>
        private val dy = arrayOf(0, -1, 0, 1) // 오, 위, 왼, 아
        private val dx = arrayOf(1, 0, -1, 0)
    }

    fun solution() {
        N = readln().toInt()
        map = Array(L) { BooleanArray(L) }

        for (i in 1..N) {
            val st = StringTokenizer(readln())
            val x = st.nextToken().toInt()
            val y = st.nextToken().toInt()
            val d = st.nextToken().toInt()
            val g = st.nextToken().toInt()
            history = LinkedList()
            stack = Stack()
            history.add(Dragon(d))
            draw(0, g, y, x)
        }

        print(count())
    }

    private fun count(): Int {
        var t = 0
        for (i in 0 until L - 1) {
            for (j in 0 until L - 1) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    t++
                }
            }
        }

        return t
    }

    private fun draw(cur: Int, gen: Int, curY: Int, curX: Int) {
        if (cur > gen) return

        var cy = curY
        var cx = curX
        map[cy][cx] = true

        if (cur == 0) {
            val d = history[0].d
            cy += dy[d]
            cx += dx[d]
            map[cy][cx] = true
            draw(1, gen, cy, cx)
        } else {
            stack.addAll(history)
            while (stack.isNotEmpty()) {
                val before = stack.pop()
                val turnedDir = turn(before.d)
                cy += dy[turnedDir]
                cx += dx[turnedDir]
                map[cy][cx] = true
                history.add(Dragon(turnedDir))
            }
            draw(cur + 1, gen, cy, cx)
        }
    }

    private fun turn(dir: Int): Int {
        return (dir + 1) % 4
    }

    private data class Dragon(
        val d: Int,
    )
}

fun main() {
    Solution18685().solution()
}