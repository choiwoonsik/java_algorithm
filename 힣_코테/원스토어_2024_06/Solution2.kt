package 힣_코테.원스토어_2024_06

import kotlin.math.min

class Solution2 {
    private val mutableMap: MutableMap<Int, List<Int>> = mutableMapOf()
    fun solution(n: Int): Int {
        return n.toString(2).count { it == '1' }
    }

    class Test {
        val mutableMap: MutableMap<Int, List<Test2>> = mutableMapOf()
    }

    data class Test2(
        val mutableMap: MutableMap<Int, List<Int>> = mutableMapOf()
    ) {
        fun dd() {
            val minByOrNull = listOf(Hi("1", 1), Hi("2", 2)).minByOrNull { it.name }

            Array<String>(3) { "" }

            val list = listOf("")
            for (i in list.indices) {

            }

            arrayOf("1", "2").sortedArray()

            val mutableListOf = mutableListOf<String>()
            mutableListOf.sort()
            mutableListOf.toTypedArray()
            min(1, 2)
        }
    }

    data class Hi(
        val name: String,
        val age: Int,
    )
}