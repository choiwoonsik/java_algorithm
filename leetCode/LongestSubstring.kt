package leetCode

import kotlin.math.max

class LongestSubstring {
    class Solution {
        var max = 1
        fun lengthOfLongestSubstring(s: String): Int {
            val set = mutableSetOf<Char>()
            if (s == "") return 0

            var left = 0
            var right = 0
            while (right < s.length) {
                if (s[right] in set) {
                    set.remove(s[left])
                    left++
                } else {
                    set.add(s[right])
                    right++
                    updateMax(right - left)
                }
            }

            return max
        }

        private fun updateMax(dist: Int) {
            if (dist > max) max = dist
        }
    }
}

fun main() {
    val lengthOfLongestSubstring = LongestSubstring.Solution().lengthOfLongestSubstring(" ")
    println(lengthOfLongestSubstring)
}