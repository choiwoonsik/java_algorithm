package 코틀린_코테스터디.week11

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class puyo_puyo_11559 {
    private var board = Array(12) { CharArray(6) }
    private var visited = Array(12) { BooleanArray(6) }
    private var stack = Stack<Dot>()
    private var dy = intArrayOf(-1, 1, 0, 0)
    private var dx = intArrayOf(0, 0, -1, 1)
    private var RGBPY = charArrayOf('R', 'G', 'B', 'P', 'Y')
    private var boomCount = 0

    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st: String
        for (y in 0..11) {
            st = br.readLine()
            for (x in 0..5) {
                board[y][x] = st[x]
            }
        }
        while (true) {
            visited = Array(12) { BooleanArray(6) }
            for (y in 0..11) {
                for (x in 0..5) {
                    if (board[y][x] != '.' && !visited[y][x]) bfs(y, x)
                }
            }
            if (stack.isEmpty()) {
                println(boomCount)
                break
            } else {
                boomCount++
                boomColor()
            }
            goDownBomb()
        }
    }

    private fun goDownBomb() {
        val line = BooleanArray(6)
        Arrays.fill(line, false)
        var height: Int
        for (y in 11 downTo 1) {
            for (x in 0..5) {
                height = y
                if (board[height][x] == '.' && !line[x]) {
                    for (h in height - 1 downTo 0) {
                        if (board[h][x] != '.') {
                            board[height][x] = board[h][x]
                            board[h][x] = '.'
                            if (height >= 1) height--
                        }
                    }
                    line[x] = true
                }
            }
        }
    }

    private fun boomColor() {
        visited = Array(12) { BooleanArray(6) }
        while (!stack.isEmpty()) {
            val getColor = stack.pop()
            val color = board[getColor.y][getColor.x]
            val que: Queue<Dot> = LinkedList()
            que.add(getColor)
            while (!que.isEmpty()) {
                val now = que.poll()
                board[now.y][now.x] = '.'
                for (i in 0..3) {
                    val nY = now.y + dy[i]
                    val nX = now.x + dx[i]
                    if (nY in 0..11 && nX >= 0 && nX < 6) {
                        if (board[nY][nX] == color && !visited[nY][nX]) {
                            visited[nY][nX] = true
                            board[nY][nX] = '.'
                            que.add(Dot(nY, nX))
                        }
                    }
                }
            }
        }
    }

    private fun bfs(y: Int, x: Int) {
        val que: Queue<Dot> = LinkedList()
        que.add(Dot(y, x))
        visited[y][x] = true
        var cnt = 0
        for (color in 0..4) {
            if (board[y][x] == RGBPY[color]) {
                while (!que.isEmpty()) {
                    val now = que.poll()
                    cnt++
                    for (i in 0..3) {
                        val nY = now.y + dy[i]
                        val nX = now.x + dx[i]
                        if (nY in 0..11 && nX >= 0 && nX < 6) {
                            if (board[nY][nX] == RGBPY[color] && !visited[nY][nX]) {
                                que.add(Dot(nY, nX))
                                visited[nY][nX] = true
                            }
                        }
                    }
                }
                if (cnt >= 4) stack.add(Dot(y, x))
            }
        }
    }

    private class Dot(var y: Int, var x: Int)
}

fun main() {
    val puyo = puyo_puyo_11559()
    puyo.solution()
}