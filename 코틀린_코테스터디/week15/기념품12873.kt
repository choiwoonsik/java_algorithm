package 코틀린_코테스터디.week15

import kotlin.math.pow

class Solution12873 {
    fun solution() {
        var stage = 1
        val n = readln().toInt()
        val list = mutableListOf<Int>()
        for (i in 1..n) list.add(i)
        var pos = 0L

        while (true) {
            if (list.size == 1) break

            val move = stage.toDouble().pow(3.0).toLong()
            pos += move
            pos %= list.size
            var target = pos - 1
            if (pos - 1 == -1L) target = list.size - 1L
            val nextNum = list[pos.toInt()]

            list.removeAt(target.toInt())
            val nextIdx = list.indexOf(nextNum)
            pos = nextIdx.toLong()

            stage++
        }

        print(list.first())
    }
}

fun main() {
    Solution12873().solution()
}
// 1 2 3
/*
1
3
 */