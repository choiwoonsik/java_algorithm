package 브루투포스

import kotlin.math.abs
import kotlin.math.max

class 고층건물_1027 {

    /*
    15
    1 5 3 2 6 3 2 6 4 2 5 7 3 1 5

    좌, 우 탐색, 가장 최근 기울기로 초기화, 해당 기울기보다 미만이어야 보임. 이상이면 패스
     */
    private var N: Int = 0
    private lateinit var buildings: DoubleArray

    fun solution() {
        N = readln().toInt()
        buildings = DoubleArray(N)

        val buildingHeight = readln().split(" ")
        for (i in 0 until N) {
            val h = buildingHeight[i].toDouble()
            buildings[i] = h
        }

        var maxCount = 0
        for (buildingIndex in 0 until N) {
            maxCount = max(maxCount, find(buildingIndex))
        }

        println(maxCount)
    }

    private fun find(index: Int): Int {
        val leftCount = leftFind(index - 1, 0)
        val rightCount = rightFind(index + 1, N - 1)

        return leftCount + rightCount
    }

    private fun leftFind(start: Int, end: Int): Int {
        val myIndex = start + 1
        val myH = buildings[myIndex]
        var lastGraph: Double? = null
        var count = 0

        for (nextIndex in start downTo end) {
            if (nextIndex == myIndex) continue
            val nextGraph = calcGraph(myH, buildings[nextIndex], myIndex - nextIndex)
            if (lastGraph == null) {
                lastGraph = nextGraph
                count++
            } else if (nextGraph < lastGraph) {
                lastGraph = nextGraph
                count++
            } else continue
        }

        return count
    }

    private fun rightFind(start: Int, end: Int): Int {
        val myIndex = start - 1
        val myH = buildings[myIndex]
        var lastGraph: Double? = null
        var count = 0

        for (nextIndex in start..end) {
            if (nextIndex == myIndex) continue
            val nextGraph = calcGraph(myH, buildings[nextIndex], abs(myIndex - nextIndex))
            if (lastGraph == null) {
                lastGraph = nextGraph
                count++
            } else if (nextGraph < lastGraph) {
                lastGraph = nextGraph
                count++
            } else continue
        }

        return count
    }

    private fun calcGraph(myH: Double, nextH: Double, width: Int): Double {
        return (myH - nextH) / width
    }
}

fun main() {
    val problem = 고층건물_1027()
    problem.solution()
}