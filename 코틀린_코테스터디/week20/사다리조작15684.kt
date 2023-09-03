package 코틀린_코테스터디.week20

class Solution사다리조작 {

    companion object {
        private lateinit var map: Array<BooleanArray>
        private var col: Int = 0
        private var installed: Int = 0
        private var row: Int = 0
    }

    fun solution() {
        val line = readln().split(" ").map { it.toInt() }
        col = line[0]
        installed = line[1]
        row = line[2]

        map = Array(row + 1) { BooleanArray(col + 1) }
        repeat(installed) {
            val dot = readln().split(" ").map { it.toInt() }
            val y = dot[0]
            val x = dot[1]
            map[y][x] = true
        }

        for (bridgeCount in 0..3) {
            if (dfs(1, 0, 0, bridgeCount)) {
                print(bridgeCount)
                return
            }
        }

        print(-1)
    }

    private fun dfs(y: Int, x: Int, installCount: Int, bridgeCount: Int): Boolean {
        if (installCount == bridgeCount) return isOk()

        var cy = y
        var cx = x

        if (cx < col) cx++
        else if (cy < row) {
            cy++
            cx = 1
        } else return false

        var result = false
        if (cx < col && !map[cy][cx] && !map[cy][cx + 1] && !map[cy][cx - 1]) {
            map[cy][cx] = true
            result = dfs(cy, cx, installCount + 1, bridgeCount)
            map[cy][cx] = false
        }

        return (result || dfs(cy, cx, installCount, bridgeCount))
    }

    private fun isOk(): Boolean {
        for (x in 1..col) {
            var start = x
            for (y in 1..row) {
                if (map[y][start]) start++
                else if (map[y][start - 1]) start--
            }
            if (start != x) return false
        }
        return true
    }
}

fun main() {
    Solution사다리조작().solution()
}