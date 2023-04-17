package 코틀린_코테스터디.week00

import java.util.LinkedList

class Solution16919 {

    companion object {
        private var R: Int = 0
        private var C: Int = 0
        private var N: Int = 0
        private val dy = arrayOf(-1, 1, 0, 0)
        private val dx = arrayOf(0, 0, -1, 1)
        private lateinit var map: Array<IntArray>
    }

    fun solution() {
        val line = readln().trim().split(" ")
        R = line[0].toInt()
        C = line[1].toInt()
        N = line[2].toInt()

        map = Array(R) { IntArray(C) { 1 } }

        for (r in 0 until R) {
            val l = readln().trim().toCharArray()
            for (c in 0 until C) {
                map[r][c] = if (l[c] == 'O') 0 else 2
            }
        }

        // 처음 1초 대기
        if (N >= 5) {
            if (N % 2 == 0) N = 2
            if (N % 4 == 1) N = 5
            if (N % 4 == 3) N = 3
        }
        for (time in 1..N) {
            bomb(time)
        }

        printMap(N)
    }

    private fun bomb(time: Int) {
        val bombQ = LinkedList<Pair<Int, Int>>()
        for (r in 0 until R) {
            for (c in 0 until C) {
                if (map[r][c] == time - 3) {
                    bombQ.add(r to c)
                }
            }
        }

        while (bombQ.isNotEmpty()) {
            val (r, c) = bombQ.poll()
            map[r][c] = time + 1
            for (dir in 0..3) {
                val ny = dy[dir] + r
                val nx = dx[dir] + c
                if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue
                map[ny][nx] = time + 1
            }
        }
    }

    private fun printMap(t: Int) {
        val answer = StringBuilder()
        for (r in 0 until R) {
            for (c in 0 until C) {
//                answer.append(map[r][c])
                answer.append(if (map[r][c] >= t - 2 && map[r][c] <= t) 'O' else '.')
            }
            answer.append("\n")
        }
        print(answer)
    }
}

fun main() {
    Solution16919().solution()
}