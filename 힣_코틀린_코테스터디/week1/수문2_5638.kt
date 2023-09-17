package 힣_코틀린_코테스터디.week1

import java.io.BufferedReader
import java.io.InputStreamReader
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
private var br = BufferedReader(InputStreamReader(System.`in`))
private lateinit var dams: Array<Pair<Long, Long>>
private var MIN_COST = Long.MAX_VALUE
private val sb = StringBuilder()

fun main() {
    initGate()
    findMinCost()
    print(sb)
}

fun calc(curIdx: Int, nowWater: Long, nowCost: Long, targetWater: Long, maxTime: Long) {
    for (cur in curIdx until N) {
        if (nowWater + dams[cur].first * maxTime >= targetWater) {
            if (nowCost + dams[cur].second < MIN_COST) {
                MIN_COST = nowCost + dams[cur].second
            }
        } else calc(
            cur + 1,
            nowWater + dams[cur].first * maxTime,
            nowCost + dams[cur].second,
            targetWater,
            maxTime
        )
    }
}

fun findMinCost() {
    M = br.readLine().toInt()
    repeat(M) { c ->
        val st = StringTokenizer(br.readLine())
        val targetWater = st.nextToken().toLong()
        val maxTime = st.nextToken().toLong()
        MIN_COST = Long.MAX_VALUE
        calc(0, 0, 0, targetWater, maxTime)
        sb.append("Case ${c + 1}: ").append(if (MIN_COST == Long.MAX_VALUE) "IMPOSSIBLE" else MIN_COST).append("\n")
    }
}

fun initGate() {
    N = br.readLine().toInt()
    dams = Array(N + 1) { Pair(0, 0) }
    repeat(N) { i ->
        val st = StringTokenizer(br.readLine())
        val water = st.nextToken().toLong()
        val cost = st.nextToken().toLong()
        dams[i] = water to cost
    }
}
