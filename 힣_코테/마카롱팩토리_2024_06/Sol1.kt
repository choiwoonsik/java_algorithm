package 힣_코테.마카롱팩토리_2024_06

class Sol1 {
    fun solution(numbers: IntArray): Int {
        val numberMap = numbers.groupBy { it }
        val moreThanHalf = numberMap.firstNotNullOfOrNull { if (it.value.size > numbers.size / 2) it.key else null }

        return moreThanHalf ?: -1
    }
}