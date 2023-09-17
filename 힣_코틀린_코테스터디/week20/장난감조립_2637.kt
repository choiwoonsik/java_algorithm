package 힣_코틀린_코테스터디.week20

import java.lang.StringBuilder
import java.util.LinkedList

class Solution2637 {
    companion object {
        private lateinit var adj: Array<LinkedList<NeedCount>>
        private lateinit var counting: MutableMap<Int, Int>
        private lateinit var basicSet: MutableSet<Int>
        private var N: Int = 0
        private var M: Int = 0
    }

    private data class NeedCount(
        val need: Int,
        val count: Int,
    )

    fun solution() {
        N = readln().toInt()
        M = readln().toInt()
        adj = Array(N + 1) { LinkedList() }
        counting = mutableMapOf()
        basicSet = mutableSetOf()

        repeat(M) {
            val line = readln().split(" ").map { it.toInt() }
            val target = line[0]
            val need = line[1]
            val count = line[2]

            adj[target].add(NeedCount(need, count))
        }

        for (i in 1 until N) {
            if (adj[i].isEmpty()) basicSet.add(i)
        }

        dfs(N, 1)
        result()
    }

    private fun result() {
        val s = StringBuilder()
        for (i in 1 until N) {
            if (counting[i] != null && basicSet.contains(i)) {
                s.append("$i ${counting[i]}\n")
            }
        }
        print(s)
    }

    // TODO: 이미 만든 부품에 대해 메모이제이션
    private fun dfs(target: Int, outCount: Int) {
        for (needCount in adj[target]) {
            val (need, count) = needCount

            if (adj[need].isEmpty()) addCount(need, outCount * count)
            else dfs(need, outCount * count)
        }
    }

    private fun addCount(need: Int, count: Int) {
        if (counting[need] == null) {
            counting[need] = count
        } else counting[need] = counting[need]!! + count
    }
}

fun main() {
    Solution2637().solution()
}