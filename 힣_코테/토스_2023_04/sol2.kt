package 힣_코테.토스_2023_04

class SolutionToss2 {

    fun solution(levels: IntArray): Int {
        val size = levels.size
        val top25PercentSize = size / 4

        if (top25PercentSize < 1) return -1

        levels.sortDescending()

        return levels[top25PercentSize-1]
    }
}

fun main() {
    SolutionToss2().solution(intArrayOf(1,2,3,4))
    SolutionToss2().solution(intArrayOf(1,2,3,4,5,6,7,8,9))
    SolutionToss2().solution(intArrayOf(1,2,3,4,5,6,7,8,9, 10))
    SolutionToss2().solution(intArrayOf(1,2))
    SolutionToss2().solution(intArrayOf(1,2,3,4))

    for (i in 1 .. 100) {
        print("$i, ")
    }
}