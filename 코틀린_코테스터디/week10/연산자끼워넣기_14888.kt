package 코틀린_코테스터디.week10

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.IllegalArgumentException
import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

/*
6
1 2 3 4 5 6
2 1 1 1
 */

class 연산자끼워넣기_14888 {
    private var N: Int = 0
    private var max = 0
    private var min = Int.MAX_VALUE
    private lateinit var nums: IntArray
    private lateinit var case: IntArray

    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        N = br.readLine().toInt()

        nums = IntArray(N)
        case = IntArray(4)

        var st = StringTokenizer(br.readLine())
        repeat(N) { i ->
            nums[i] = st.nextToken().toInt()
        }

        st = StringTokenizer(br.readLine())
        repeat(4) { i ->
            case[i] = st.nextToken().toInt()
        }

        call(1, nums[0])
        print("$max\n$min")
    }

    private fun call(curIdx: Int, curValue: Int) {
        if (curIdx == N) {
            max = max(max, curValue)
            min = min(min, curValue)
            return
        }

        if (case[0] >= 1) {
            case[0]--
            call(curIdx + 1, calc(curValue, 0, nums[curIdx]))
            case[0]++
        }
        if (case[1] >= 1) {
            case[1]--
            call(curIdx + 1, calc(curValue, 1, nums[curIdx]))
            case[1]++
        }
        if (case[2] >= 1) {
            case[2]--
            call(curIdx + 1, calc(curValue, 2, nums[curIdx]))
            case[2]++
        }
        if (case[3] >= 1) {
            case[3]--
            call(curIdx + 1, calc(curValue, 3, nums[curIdx]))
            case[3]++
        }
    }

    private fun calc(curNum: Int, calcType: Int, nextNum: Int): Int {
        return when (calcType) {
            0 -> curNum + nextNum
            1 -> curNum - nextNum
            2 -> curNum * nextNum
            3 -> curNum / nextNum
            else -> throw IllegalArgumentException("미정의 값")
        }
    }
}

fun main() {
    val problem = 연산자끼워넣기_14888()
    problem.solution()
}