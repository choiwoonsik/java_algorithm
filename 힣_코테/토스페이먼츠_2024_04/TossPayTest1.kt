package 힣_코테.토스페이먼츠_2024_04

class TossPayTest1 {
    fun solution(levels: IntArray): Int {
        if (levels.size < 4) return -1
        val hardIndex = (levels.size / 4) - 1
        return levels.sortedDescending()[hardIndex]
    }
}