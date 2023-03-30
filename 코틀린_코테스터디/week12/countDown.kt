package 코틀린_코테스터디.week12

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class Solution {
    private val nums = 1..20
    private lateinit var numberList: ArrayList<Int>
    private val queue = PriorityQueue(Comparator { d1: Dto, d2: Dto ->
        if (d1.depth != d2.depth) return@Comparator d1.depth.compareTo(d2.depth)
        else return@Comparator (-d1.singleBall).compareTo(-d2.singleBall)
    })

    fun solution(target: Int): IntArray {

        numberList = arrayListOf()

        for (n in nums) {
            numberList.add(n)
            numberList.add(n * 2)
            numberList.add(n * 3)
        }
        numberList.add(50)

        recursive(target, 0, 0)
        val ret = queue.poll()
        print("${ret.depth}, ${ret.singleBall}")

        return intArrayOf()
    }

    fun recursive(curPt: Int, singleBall: Int, depth: Int) {
        if (curPt == 0) {
            queue.add(Dto(singleBall, depth))
        }

        for (num in numberList) {
            if (num > curPt) continue

            val nextPt = curPt - num
            val single = if (num <= 20) 1 else 0
            val ball = if (num == 50) 1 else 0
            recursive(nextPt, singleBall + single + ball, depth + 1)
        }
    }

    data class Dto(
        val singleBall: Int,
        val depth: Int,
    )
}