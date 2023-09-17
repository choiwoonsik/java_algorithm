package 힣_코틀린_코테스터디.week11

import 힣_코틀린_코테스터디.week11.Color.Companion.isColor
import 힣_코틀린_코테스터디.week11.Color.Companion.isSame
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/*
......
......
......
......
......
......
......
......
.Y....
.YG...
RRYG..
RRYGG.
 */

private data class Dot(
    val y: Int,
    val x: Int,
)

private enum class Color(val code: String) {
    R("R"),
    G("G"),
    B("B"),
    P("P"),
    Y("Y"),
    D(".");

    companion object {
        fun find(code: String): Color {
            return Color.values().first { it.code == code }
        }

        fun Color.isColor(): Boolean {
            return this != D
        }

        fun Color.isSame(color: Color): Boolean {
            return this == color
        }
    }
}

class PuyoPuyo_11559 {
    val dy = arrayOf(-1, 1, 0, 0)
    val dx = arrayOf(0, 0, -1, 1)
    private val map = Array(14) { Array(8) { Color.D } }
    val br = BufferedReader(InputStreamReader(System.`in`))
    private var bombCount = 0
    private val bombQueues = LinkedList<Dot>()
    private var visited = Array(14) { BooleanArray(8) { false } }

    fun solution() {
        initMap()
        game()
        print(bombCount)
    }

    private fun game() {
        while (true) {
            findBombColor(map)
            if (bombQueues.isEmpty()) return
            bombColor()
            gravityPuyo()
            bombCount++
        }
    }

    private fun findBombColor(map: Array<Array<Color>>) {
        for (i in 12 downTo 1) {
            for (j in 6 downTo 1) {
                if (!visited[i][j] && map[i][j].isColor()) {
                    bombBfs(Dot(i, j))
                }
            }
        }
    }

    private fun bombBfs(dot: Dot) {
        val origin = map[dot.y][dot.x]
        val queue = LinkedList<Dot>()
        val bombQueue = LinkedList<Dot>()
        queue.add(dot)

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (dir in 0..3) {
                val ny = cur.y + dy[dir]
                val nx = cur.x + dx[dir]

                if (ny >= 13 || ny <= 0 || nx >= 7 || nx <= 0) continue
                if (visited[ny][nx]) continue
                if (!map[ny][nx].isColor()) continue
                if (!map[ny][nx].isSame(origin)) continue

                visited[ny][nx] = true
                val nextDot = Dot(ny, nx)
                queue.add(nextDot)
                bombQueue.add(nextDot)
            }
        }
        if (bombQueue.size >= 4) {
            bombQueues.addAll(bombQueue)
            bombQueues.add(dot)
        }
    }

    private fun bombColor() {
        visited = Array(14) { BooleanArray(8) { false } }
        val downColumnSet = mutableSetOf<Int>()
        while (bombQueues.isNotEmpty()) {
            val (y, x) = bombQueues.poll()
            map[y][x] = Color.D
            downColumnSet.add(x)
        }
    }

    private fun gravityPuyo() {
        val line = BooleanArray(7)
        Arrays.fill(line, false)
        var height: Int
        for (y in 12 downTo 1) {
            for (x in 1..6) {
                height = y
                if (map[height][x] == Color.D && !line[x]) {
                    for (h in height - 1 downTo 0) {
                        if (map[h][x] != Color.D) {
                            map[height][x] = map[h][x]
                            map[h][x] = Color.D
                            if (height >= 1) height--
                        }
                    }
                    line[x] = true
                }
            }
        }
    }

    private fun initMap() {
        for (i in 1..12) {
            val line = br.readLine().toCharArray()
            for (j in 1..6) {
                map[i][j] = Color.find(line[j - 1].toString())
            }
        }
    }


}

fun main() {
    val problem = PuyoPuyo_11559()
    problem.solution()
}