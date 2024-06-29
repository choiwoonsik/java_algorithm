package leetCode

import kotlin.math.abs

class LongestPalindromicSubstring {
    class Solution {
        lateinit var dp: Array<BooleanArray>

        fun longestPalindrome(s: String): String {
            dp = Array(s.length) { BooleanArray(s.length) }

            var flagFix = 0
            var flagMove = 0
            val last = s.length - 1
            var maxFlagFix = 0
            var maxFlagMove = 0

            while (flagFix <= last) {
                flagMove = flagFix
                while (flagMove >= 0) {
                    if (flagMove == flagFix) dp[flagFix][flagMove] = true
                    else if (flagFix - flagMove == 1) dp[flagFix][flagMove] = s[flagFix] == s[flagMove]
                    else dp[flagFix][flagMove] = s[flagFix] == s[flagMove] && dp[flagFix - 1][flagMove + 1]

                    if (dp[flagFix][flagMove] && abs(maxFlagFix - maxFlagMove) < abs(flagFix - flagMove)) {
                        maxFlagFix = flagFix
                        maxFlagMove = flagMove
                    }
                    flagMove--
                }
                flagFix++
            }

            return s.substring(maxFlagMove, maxFlagFix + 1)
        }
    }
}

fun main() {
    LongestPalindromicSubstring.Solution().longestPalindrome("hih")
}
