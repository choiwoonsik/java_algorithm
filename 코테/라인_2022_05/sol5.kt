package 코테.라인_2022_05

import java.util.StringTokenizer

class sol5 {
    private var MAX: Int = 0

    private val totalMap = mutableMapOf<String, Int>()
    private val dayMap = mutableMapOf<String, Int>()

    fun solution(id_list: Array<String>, k: Int): Int {

        MAX = k

        for (ids in id_list) {
            dayMap.clear()
            val st = StringTokenizer(ids, " ")
            while (st.hasMoreTokens()) {
                val customer = st.nextToken()
                dayMap[customer] = 1
            }
            for (cus in dayMap.keys) {
                if (totalMap.containsKey(cus)) {
                    totalMap[cus] = totalMap[cus]!!.toInt() + 1
                    if (totalMap[cus]!! >= MAX) totalMap[cus] = MAX
                } else totalMap[cus] = 1
            }
        }

        var sum = 0
        for (cus in totalMap.keys) {
            sum += totalMap[cus]!!.toInt()
        }

        return sum
    }
}