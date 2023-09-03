package 브루투포스

import java.util.LinkedList
import kotlin.math.min

/*
4 4
####
#JF#
#..#
#..#
 */
class 불_4719 {
    private var R: Int = 0
    private var C: Int = 0
    private lateinit var map: Array<Array<String>>
    private lateinit var fireQ: LinkedList<Dot>
    private lateinit var myQ: LinkedList<Dot>
    private val dy = listOf(-1, 1, 0, 0)
    private val dx = listOf(0, 0, -1, 1)
    private val WALL = "#"
    private val FIRE = "F"
    private val MY = "J"
    private var escapeCount = Int.MAX_VALUE

    fun solution() {
        val rc = readln().split(" ")
        R = rc[0].toInt()
        C = rc[1].toInt()
        map = Array(R) { Array(C) { "" } }
        myQ = LinkedList()
        fireQ = LinkedList()
        var me = Dot(-1, -1, 0)
        var fire: Dot

        for (r in 0 until R) {
            val line = readln().toCharArray()
            for (c in 0 until C) {
                map[r][c] = line[c].toString()
                if (map[r][c] == "J") me = Dot(r, c, 1)
                if (map[r][c] == "F") {
                    fire = Dot(r, c, 0)
                    fireQ.add(fire)
                }
            }
        }

        myQ.add((me))
        bfs()
    }

    private fun bfs() {
        while (myQ.isNotEmpty()) {
            val fireSize = fireQ.size
            val meSize = myQ.size
            repeat(fireSize) { fire() }
            repeat(meSize) { meMove() }
        }

        if (escapeCount != Int.MAX_VALUE) println(escapeCount) else println("IMPOSSIBLE")
    }

    private fun meMove() {
        val now = myQ.poll()

        if (escape(now)) {
            escapeCount = min(escapeCount, now.move)
            return
        }

        for (d in 0 until 4) {
            val ny = dy[d] + now.y
            val nx = dx[d] + now.x
            val nm = now.move + 1

            if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue
            if (map[ny][nx] == MY) continue
            if (map[ny][nx] == WALL) continue
            if (map[ny][nx] == FIRE) continue

            map[ny][nx] = MY
            myQ.add(Dot(ny, nx, nm))
        }
    }

    private fun escape(now: Dot): Boolean {
        return now.y == R - 1 || now.y == 0 || now.x == C - 1 || now.x == 0
    }

    private fun fire() {
        val now = fireQ.poll()

        for (d in 0 until 4) {
            val ny = dy[d] + now.y
            val nx = dx[d] + now.x

            if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue
            if (map[ny][nx] == WALL) continue
            if (map[ny][nx] == FIRE) continue

            map[ny][nx] = FIRE
            fireQ.add(Dot(ny, nx, 0))
        }
    }

    private data class Dot(
        val y: Int,
        val x: Int,
        val move: Int,
    )
}

fun main() {
    val solution = 불_4719()
    solution.solution()
}