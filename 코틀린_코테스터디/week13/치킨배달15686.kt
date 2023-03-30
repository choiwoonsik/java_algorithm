package 코틀린_코테스터디.week13

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.abs
import kotlin.math.min

/*
5 3
0 0 1 0 0
0 0 2 0 1
0 1 2 0 0
0 0 1 0 0
0 0 0 0 2
 */
class Problem15686 {
    private var N: Int = 0
    private var M: Int = 0
    private var min = Int.MAX_VALUE
    private lateinit var map: Array<IntArray>
    private lateinit var visited: Array<BooleanArray>
    private val chickenHouses: MutableList<Dot> = mutableListOf()
    private val house: MutableList<Dot> = mutableListOf()

    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val st = StringTokenizer(br.readLine())
        N = st.nextToken().toInt()
        M = st.nextToken().toInt()
        map = Array(N) { IntArray(N) }
        visited = Array(N) { BooleanArray(N) }

        repeat(N) { i ->
            val line = StringTokenizer(br.readLine())
            repeat(N) { j ->
                map[i][j] = line.nextToken().toInt()
                if (map[i][j] == 2) chickenHouses.add(Dot(i, j))
                else if (map[i][j] == 1) house.add(Dot(i, j))
            }
        }

        recursive(0, 0)
        print(min)
    }

    private fun recursive(cur: Int, depth: Int) {
        if (depth == M) {
            val dist = calcChickenDist()
            min = min(min, dist)
        }

        for (pos in cur until chickenHouses.size) {
            val (y, x) = chickenHouses[pos]
            if (!visited[y][x]) {
                visited[y][x] = true
                recursive(pos + 1, depth + 1)
                visited[y][x] = false
            }
        }
    }

    private fun calcChickenDist(): Int {
        var sum = 0
        for (from in house) {
            var minChickenDist = Int.MAX_VALUE
            for (to in chickenHouses) {
                if (visited[to.y][to.x]) {
                    val dist = abs(from.y - to.y) + abs(from.x - to.x)
                    minChickenDist = min(minChickenDist, dist)
                }
            }
            sum += minChickenDist
        }

        return sum
    }

    data class Dot(
        val y: Int,
        val x: Int
    )
}

fun main() {
    Problem15686().solution()
}