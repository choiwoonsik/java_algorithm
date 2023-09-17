package 힣_코틀린_코테스터디.week1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Long.min
import java.lang.StringBuilder
import java.util.StringTokenizer

/*
4
720000 120000
50000 60000
130000 50000
1200000 150000
3
5000000 7
5000000 30
63000000 24
 */

private var N: Int = 0
private var M: Int = 0
private lateinit var G: MutableList<Pair<Long, Int>>
private lateinit var tableList: MutableList<Pair<Long, Int>>
private val sb = StringBuilder()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    N = br.readLine().toInt()
    tableList = mutableListOf()
    G = mutableListOf()

    repeat(N) {
        val st = StringTokenizer(br.readLine())
        val water = st.nextToken().trim().toLong()
        val cost = st.nextToken().trim().toInt()
        G.add(water to cost)
    }
    possibleWaterTable()
//    tableList.map { println("물: ${it.first}, 비용: ${it.second}") }

    M = br.readLine().toInt()
    repeat(M) {i ->
        val st = StringTokenizer(br.readLine())
        val w = st.nextToken().trim().toLong()
        val h = st.nextToken().trim().toInt()
        calc(i, w, h)
    }
    print(sb)
}

private fun possibleWaterTable() {
    for (idx in 0 until N) tableList.add(G[idx].first to G[idx].second)

    for (size in 1 until N) {
        for (start in 0 until N - size) {
            for (end in start + size until N) {
                val startSum =
                    G.mapIndexed { idx, pair ->
                        if (idx >= start && idx < start + size) pair.first else 0
                    }.sum()
                val costSum =
                    G.mapIndexed { idx, pair ->
                        if (idx >= start && idx < start + size) pair.second else 0
                    }.sum()
                val plusSum = G[end].first
                val plusCost = G[end].second
                tableList.add(startSum + plusSum to costSum + plusCost)
            }
        }
    }
}

private fun calc(idx: Int, targetWater: Long, maxHour: Int) {
    var min = Long.MAX_VALUE
    for ((water, cost) in tableList) {
        if (water * maxHour >= targetWater) {
            min = min(min, cost.toLong())
        }
    }
    if (min != Long.MAX_VALUE) sb.append("Case ${idx + 1}: $min\n")
    else sb.append("Case ${idx + 1}: IMPOSSIBLE\n")
}