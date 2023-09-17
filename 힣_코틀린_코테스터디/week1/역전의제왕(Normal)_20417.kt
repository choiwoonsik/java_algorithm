package 힣_코틀린_코테스터디.week1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.StringTokenizer

/*
3 18
00:01 3 1 1
00:01 1 1 1
00:01 2 1 1
03:02 1 2 1
03:02 2 2 1
03:02 3 2 1
03:03 1 3 1
03:03 2 3 1
03:03 3 3 1
03:04 1 4 1
03:04 3 4 1
03:04 2 4 1
03:05 2 5 1
03:05 1 5 1
03:05 3 5 1
03:06 2 6 1
03:06 3 6 1
03:06 1 6 1
 */

private var N: Int =  0
private var M: Int = 0
private var needUnFreezeCount: Int = 0
private lateinit var dashboard: MutableMap<Int, Penalty>
private lateinit var freezeDashboard: MutableMap<Int, MutableList<User>>
private lateinit var freezeSolved: MutableMap<Int, PriorityQueue<Int>>
private lateinit var prizeMap: MutableMap<Int, Int>
private lateinit var sortedBy: List<Pair<Int, Penalty>>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    dashboard = mutableMapOf()
    freezeDashboard = mutableMapOf()
    freezeSolved = mutableMapOf()
    prizeMap = mutableMapOf()

    repeat(M) {
        st = StringTokenizer(br.readLine())
        val time = st.nextToken().toTime()
        val id = st.nextToken().toInt()
        val problem = st.nextToken().toInt()
        val tried = st.nextToken().toInt()

        if (time <= 180) beforeFreezing(time, id, problem, tried)
        else freezing(time, id, problem, tried)
    }
    repeat(needUnFreezeCount) {
        unfreeze()
    }

    val winners = prizeMap.toList().sortedBy { it.second }
    val max = winners.maxBy { it.second }.second
    val groups = winners.groupBy { it.second }
    var answer = 0
    if (groups[max]!!.size > 1) {
        val users = groups[max]!!.map { it.first }
        val sortedBy1 = users.sortedBy { u -> sortedBy.indexOfFirst { it -> it.first == u } }
        answer = sortedBy1.first()
    } else answer = groups[max]!!.first().first

    println(answer)
}

fun unfreeze() {
    val beforeSortedDashboard = dashboard.toList()
        .sortedBy { -it.second.penaltyPont }
        .sortedBy { it.second.solved.size }

    var lastId = 0
    var solvedProblem = 0
    var beforeIndex = 0
    for (index in beforeSortedDashboard.indices) {
         lastId = beforeSortedDashboard[index].first
        if (freezeSolved.containsKey(lastId)) {
            solvedProblem = freezeSolved[lastId]!!.poll()
            beforeIndex = index
            break
        }
    }

    val solvedUsers = freezeDashboard[solvedProblem]!!
    for (user in solvedUsers) {
        dashboard[user.id]!!.solved.add(solvedProblem)
        dashboard[user.id]!!.penaltyPont += user.penaltyPoint

        val afterSortedDashboard = dashboard.toList()
            .sortedBy { -it.second.penaltyPont }
            .sortedBy { it.second.solved.size }
        val afterIndex = afterSortedDashboard.indexOfFirst { it.first == lastId }

        val diff = afterIndex - beforeIndex
        if (!prizeMap.containsKey(lastId)) prizeMap[lastId] = 0
        prizeMap[lastId] = prizeMap[lastId]!! + diff
    }
    val sortedBy: List<Pair<Int, Penalty>> = dashboard.toList()
        .sortedBy { -it.second.penaltyPont }
        .sortedBy { it.second.solved.size }
}

private fun freezing(time: Int, id: Int, problem: Int, tried: Int) {
    needUnFreezeCount++
    if (!freezeDashboard.containsKey(problem)) freezeDashboard[problem] = mutableListOf()
    freezeDashboard[problem]!!.add(User(id, time + (tried - 1) * 20))

    if (!freezeSolved.containsKey(id)) freezeSolved[id] = PriorityQueue()
    freezeSolved[id]!!.add(problem)
}

private fun beforeFreezing(time: Int, id: Int, problem: Int, tried: Int) {
    if (!dashboard.containsKey(id)) dashboard[id] = Penalty()
    dashboard[id]!!.penaltyPont += time + (tried - 1) * 20
    dashboard[id]!!.solved.add(problem)
}

private data class Penalty(
    var penaltyPont: Int = 0,
    val solved: LinkedList<Int> = LinkedList()
)

private data class User(
    var id: Int,
    var penaltyPoint: Int = 0,
)

fun String.toTime(): Int {
    val t = this.split(":").map { it.toInt() }
    return t[0] * 60 + t[1]
}