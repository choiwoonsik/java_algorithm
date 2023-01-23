package Gold이상문제_정리

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.max
import java.util.*

private lateinit var map: Array<IntArray>
private lateinit var sub: Array<IntArray>
private var N = 0
private var MAX = 0
private var stack = Stack<Int>()
private var after = ArrayList<Int>()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st: StringTokenizer
    N = br.readLine().toInt()
    map = Array(N) { IntArray(N) }

    for (i in 0 until N) {
        st = StringTokenizer(br.readLine())
        for (j in 0 until N) {
            map[i][j] = st.nextToken().toInt()
        }
    }

    play(map, 0)
    println(MAX)
}

private fun play(board: Array<IntArray>, move: Int) {
    if (move == 5) {
        board.map { MAX = max(MAX, it.max()) }
        return
    }

    play(left(board), move + 1)
    play(right(board), move + 1)
    play(up(board), move + 1)
    play(down(board), move + 1)
}

private fun up(board: Array<IntArray>): Array<IntArray> {
    sub = Array(N) { IntArray(N) { 0 } }
    stack.clear()
    after.clear()
    for (col in 0 until N) {
        for (row in 0 until N) {
            if (board[row][col] > 0) {
                if (stack.isNotEmpty()) {
                    if (stack.peek() == board[row][col]) {
                        stack.pop()
                        after.addAll(stack)
                        after.add(board[row][col] * 2)
                        stack.clear()
                    } else stack.add(board[row][col])
                } else stack.add(board[row][col])
            }
        }
        after.addAll(stack)

        var idx = 0
        for (i in after) sub[idx++][col] = i
        stack.clear()
        after.clear()
    }
    return sub
}

private fun down(board: Array<IntArray>): Array<IntArray> {
    sub = Array(N) { IntArray(N) { 0 } }
    stack.clear()
    after.clear()
    for (col in 0 until N) {
        for (row in N - 1 downTo 0) {
            if (board[row][col] > 0) {
                if (!stack.isEmpty()) {
                    if (stack.peek() == board[row][col]) {
                        stack.pop()
                        after.addAll(stack)
                        after.add(board[row][col] * 2)
                        stack.clear()
                    } else stack.add(board[row][col])
                } else stack.add(board[row][col])
            }
        }
        after.addAll(stack)

        var idx = N - 1
        for (i in after) sub[idx--][col] = i
        stack.clear()
        after.clear()
    }
    return sub
}

private fun right(board: Array<IntArray>): Array<IntArray> {
    sub = Array(N) { IntArray(N) { 0 } }
    stack.clear()
    after.clear()
    for (row in 0 until N) {
        for (col in N - 1 downTo 0) {
            if (board[row][col] > 0) {
                if (stack.isNotEmpty()) {
                    if (stack.peek() == board[row][col]) {
                        stack.pop()
                        after.addAll(stack)
                        after.add(board[row][col] * 2)
                        stack.clear()
                    } else stack.add(board[row][col])
                } else stack.add(board[row][col])
            }
        }
        after.addAll(stack)

        var idx = N - 1
        for (i in after) sub[row][idx--] = i
        stack.clear()
        after.clear()
    }
    return sub
}

private fun left(board: Array<IntArray>): Array<IntArray> {
    sub = Array(N) { IntArray(N) { 0 } }
    stack.clear()
    after.clear()
    for (row in 0 until N) {
        for (col in 0 until N) {
            if (board[row][col] > 0) {
                if (stack.isNotEmpty()) {
                    if (stack.peek() == board[row][col]) {
                        stack.pop()
                        after.addAll(stack)
                        after.add(board[row][col] * 2)
                        stack.clear()
                    } else stack.add(board[row][col])
                } else stack.add(board[row][col])
            }
        }
        after.addAll(stack)

        var idx = 0
        for (i in after) sub[row][idx++] = i
        stack.clear()
        after.clear()
    }
    return sub
}