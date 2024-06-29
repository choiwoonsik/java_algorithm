package leetCode

class ReverseInteger {
    class Solution {
        fun reverse(x: Int): Int {
            val max = Int.MAX_VALUE.toString()
            val min = Int.MIN_VALUE.toString()
            val number = x.toString()
            val reOrderNumber = StringBuilder()
            var sign = 1

            for (num in number.reversed()) {
                if (num == '-') sign = -1
                else reOrderNumber.append(num)
            }

            val result = if (sign < 0) {
                val minusReOrderNumber = StringBuilder("-").append(reOrderNumber)
                if (minusReOrderNumber.toString().numberIsBiggerThan(min)) "0"
                else minusReOrderNumber.toString()
            } else {
                if (reOrderNumber.toString().numberIsBiggerThan(max)) "0"
                else reOrderNumber.toString()
            }

            return result.toInt()
        }

        fun String.numberIsBiggerThan(other: String): Boolean {
            return if (this.length > other.length) true
            else if (this.length < other.length) false
            else {
                for (index in indices) {
                    return if (this[index] < other[index]) false
                    else if (this[index] > other[index]) true
                    else continue
                }
                false
            }
        }
    }
}

fun main() {
    val reverse = ReverseInteger.Solution().reverse(-123)
    println(reverse)
}