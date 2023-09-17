import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

/*
5
***#*
*.!.*
*!.!*
*.!.*
*#***
 */
private lateinit var board: Array<Array<String>>
private var N: Int = 0
private data class Dot (
    val y: Int,
    val x: Int,
    val cost: Int,
    val dir: Int,
)
private data class CostDirDp(
    var cost: Int,
    var dir: Int,
)
private lateinit var dp: Array<Array<CostDirDp>>
private var start = Dot(-1, -1, 0, -1)
private var end = Dot(0, 0, 0, -1)
private val dy = arrayOf(-1, 1, 0, 0) // up, down, left, right
private val dx = arrayOf(0, 0, -1, 1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    N = br.readLine().toInt()

    dp = Array(N + 1) { Array(N + 1) { CostDirDp(100000, -1) } }
    board = Array(N + 1) { Array(N + 1) { "" } }

    repeat(N) { y ->
       val line = br.readLine().toCharArray()
        repeat(N) { x ->
            board[y][x] = line[x] + ""
            if (board[y][x] == "#" && start.y == -1) {
                val dir: Int
                when (y) {
                    0 -> dir = 1
                    N - 1 -> dir = 0
                    else -> {
                        if (x == 0) dir = 3
                        else dir = 2
                    }
                }
                start = Dot(y, x, 0, dir)
            }
            else end = Dot(y, x, 0, -1)
        }
    }

    dijkstra()
}

private fun dijkstra() {
    val pq = PriorityQueue<Dot>(compareBy { it.cost })
    dp[start.y][start.x].cost = 0
    pq.add(start)

    while (pq.isNotEmpty()) {
        val cur = pq.poll()

        if (dp[cur.y][cur.x].cost < cur.cost && dp[cur.y][cur.x].dir == cur.dir) {
            continue
        }

        if (cur.y == end.y && cur.x == end.x) {
            println(dp[cur.y][cur.x].cost)
            return
        }

        while (true) {
            val ny = cur.y + dy[cur.dir]
            val nx = cur.x + dx[cur.dir]

            if (board[ny][nx] == "*") break

            if (dp[ny][nx].cost > dp[cur.y][cur.x].cost) {
                dp[ny][nx].cost = dp[cur.y][cur.x].cost
                dp[ny][nx].dir = dp[cur.y][cur.x].dir
                var dir = 0
                if (dp[ny][nx].dir == 0) dir = 3
                if (dp[ny][nx].dir == 1) dir = 2
                if (dp[ny][nx].dir == 3) dir = 1
                if (dp[ny][nx].dir == 2) dir = 0
                pq.add(Dot(ny, nx, dp[ny][nx].cost, dir))
            }
        }
    }
}
