package 힣_코테.원스토어_2024_06

class Solution3 {
    companion object {
        var DAY_LIMIT: Int = 0
        var SEQUENCE_LIMIT = 0
        var SEQUENCE = 7
    }

    fun solution(a: Int, b: Int, usages: Array<IntArray>): Int {
        DAY_LIMIT = a
        SEQUENCE_LIMIT = b

        return usages.count { isOverLimit(it) }
    }

    private fun isOverLimit(usageList: IntArray): Boolean {
        var left = 0
        var right = 0
        var accumulateUsageSum = 0

        while (right < usageList.size) {
            val todayUsage = usageList[right]
            if (todayUsage > DAY_LIMIT) return true

            accumulateUsageSum += todayUsage

            if (right - left + 1 > SEQUENCE) {
                accumulateUsageSum -= usageList[left]
                left++
            }

            if (accumulateUsageSum > SEQUENCE_LIMIT) return true

            right++
        }

        return false
    }

    fun test(): Array<Array<String>> {
        return arrayOf(arrayOf("1", "2"))
    }
}