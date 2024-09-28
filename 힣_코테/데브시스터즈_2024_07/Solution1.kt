package 힣_코테.데브시스터즈_2024_07

class Solution1 {

    data class JellyBox(
        private val pouch: String
    ) {
        private val jellyMap = mutableMapOf<Char, Int>()
        private var delegateJelly: Char? = null

        init {
            pouch.forEach {
                jellyMap[it] = jellyMap.getOrDefault(it, 0) + 1
            }

            val (jelly, count) = jellyMap.maxBy { it.value }
            if (count > pouch.length / 2) {
                delegateJelly = jelly
            }

            jellyMap.map { it.value }.sum()
        }
    }

}

fun main() {
}