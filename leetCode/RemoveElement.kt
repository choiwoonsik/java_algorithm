package leetCode

class RemoveElement {
    class Solution {
        fun removeElement(nums: IntArray, value: Int): Int {
            val innerTargetList = mutableListOf<Int>()
            val outerTargetList = mutableListOf<Int>()

            val totalTargetCount = nums.count { it == value }
            val cutLine = nums.size - totalTargetCount

            for ((index, num) in nums.withIndex()) {
                if (index >= cutLine && num != value) outerTargetList.add(index)
                else if (index < cutLine && num == value) innerTargetList.add(index)
            }

            for (i in 0 until innerTargetList.size) {
                val innerIdx = innerTargetList[i]
                val outerIdx = outerTargetList[i]

                val temp = nums[innerIdx]
                nums[innerIdx] = nums[outerIdx]
                nums[outerIdx] = temp
            }

            return cutLine
        }
    }
}

fun main() {
    val intArrayOf = intArrayOf(0, 1, 2, 2, 3, 0, 4, 2)
    val removeElement = RemoveElement.Solution().removeElement(intArrayOf, 2)
    println(removeElement)
    println(intArrayOf)
}