package 코테.라인_2022_05

import java.util.StringTokenizer

fun main() {
    val sol = sol6()
    val solution = sol.solution("2022:01:01:00:00:00", emptyArray())
    println("${solution[0]}, ${solution[1]}")
}

class sol6 {
    private val moneyMap = mutableMapOf<Int, Int>()

    fun solution(s: String, times: Array<String>): IntArray {
        val st = StringTokenizer(s, ":")
        val y = st.nextToken().toInt()
        val m = st.nextToken().toInt()
        val d = st.nextToken().toInt()
        val hh = st.nextToken().toInt()
        val mm = st.nextToken().toInt()
        val ss = st.nextToken().toInt()

        val startDate = Date(y, m, d, hh, mm, ss)
        var nextDate = startDate
        moneyMap[startDate.toDaily()] = 1

        for (time in times) {
            val plusDay = time.toDay()
            nextDate = calcDate(nextDate, plusDay)
            moneyMap[nextDate.toDaily()] = 1
        }

        val duringDay = nextDate.toDaily() - startDate.toDaily() + 1
        val success = if (moneyMap.keys.size == duringDay) 1 else 0
        return intArrayOf(success, duringDay)
    }

    private fun calcDate(nextDate: Date, plusDay: Day): Date {
        val nowS = nextDate.ss + nextDate.mm * 60 + nextDate.hh * 3600
        val plusS = plusDay.ss + plusDay.mm * 60 + plusDay.hh * 3600

        val nextS = nowS + plusS

        val nextPlusDay = nextS / 3600 / 24
        val remainS = nextS % (3600 * 24)
        val nextHour = remainS / 3600
        val nextMin = remainS % 3600 / 60
        val nextSec = remainS % 3600 % 60

        val nextDaily = nextDate.toDaily() + plusDay.d + nextPlusDay
        val nextY = nextDaily / 360
        val nextM = (nextDaily % 360) / 30
        val nextD = nextDaily % 360 % 30

        return Date(nextY, nextM, nextD, nextHour, nextMin, nextSec)
    }

}

data class Date(
    val y: Int,
    val m: Int,
    val d: Int,
    val hh: Int,
    val mm: Int,
    val ss: Int,
)

data class Day(
    val d: Int,
    val hh: Int,
    val mm: Int,
    val ss: Int,
)

fun String.toDay(): Day {
    val day = this.split(":").map { it.toInt() }
    return Day(day[0], day[1], day[2], day[3])
}

fun Date.toDaily(): Int {
    return this.y * 360 + this.m * 30 + this.d
}