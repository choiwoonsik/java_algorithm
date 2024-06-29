package 힣_코테.뱅샐_2024_03

class LargeArray {
    fun solution() {
        val sparseArray = SparseArray()
        sparseArray.init(arrayOf(0, 1, 2, 3, 0, 0, 0), 100)
        sparseArray.set(0, 99)

        var get = sparseArray.get(0)
        println(get)
        get = sparseArray.get(3)
        println(get)
        get = sparseArray.get(6)
        println(get)
        sparseArray.set(99, 99)
        println(sparseArray.get(99))
//        sparseArray.set(100, 100)
//        println(sparseArray.get(100))
    }

    class SparseArray {
        private lateinit var map: MutableMap<Int, Any>

        companion object {
            private var MAX_SIZE: Int = 0
        }

        fun init(arr: Array<Any>, size: Int) {
            map = mutableMapOf()
            MAX_SIZE = size
            for ((index, element) in arr.withIndex()) {
                if (index >= size) break
                if (element == 0) continue
                else map[index] = element
            }
        }

        fun set(i: Int, value: Any) {
            rangeValidation(i)
            map[i] = value
        }

        fun get(i: Int): Any {
            rangeValidation(i)
            return map[i] ?: 0
        }

        private fun rangeValidation(i: Int) {
            if (i >= MAX_SIZE || i < 0) throw IllegalArgumentException("out of range")
        }
    }
}

fun main() {
    LargeArray().solution()
}