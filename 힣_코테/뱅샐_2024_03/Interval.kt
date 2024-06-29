package 힣_코테.뱅샐_2024_03

class Interval {
    class Solution {
        fun solution(input: Array<Pair<Int, Int>>): List<Pair<Int, Int>> {
            val sorted = input
                .sortedWith(
                    compareBy(
                        { it.first },
                        { -it.second },
                    )
                ).toMutableList()
            println(sorted.map { it })
            val mergeList = mutableListOf<Pair<Int, Int>>()

            for ((start, end) in sorted) {
                if (mergeList.isEmpty()) mergeList.add(start to end)
                else {
                    val (_, lastEnd) = mergeList[mergeList.lastIndex]
                    if (end <= lastEnd) continue
                    else mergeList.add(start to end)
                }
            }

            return mergeList
        }
    }
}

fun main() {
    val result = Interval.Solution().solution(
        arrayOf(
            Pair(1, 3),
            Pair(5, 8),
            Pair(5, 9),
            Pair(4, 9),
            Pair(4, 10),
            Pair(21, 25),
            Pair(20, 25),
        )
    )
    println(result.map { it })
}