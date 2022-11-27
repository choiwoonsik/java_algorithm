package 분리집합_UnionFind

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
10 9
4 1 2 3 4
2 1 5
2 2 6
1 7
1 8
2 7 8
1 9
1 10
2 3 10
1 4
 */

private var N: Int = 0
private var M: Int = 0
private lateinit var parents: IntArray
private var knownKing: Int = 0
private lateinit var partyMap: Array<MutableList<Int>>
private lateinit var myPartyListMap: MutableMap<Int, MutableList<Int>>
private var speakLieCount: Int = 0
private lateinit var visited: Array<Boolean>

fun main() {
    var br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()

    parents = IntArray(N + 1)
    partyMap = Array(M + 1) { mutableListOf() }
    visited = Array(M + 1) { false }
    myPartyListMap = mutableMapOf()
    repeat(N + 1) {
        i -> parents[i] = i
    }

    st = StringTokenizer(br.readLine())
    val knownTrueCount = st.nextToken().toInt()
    repeat(knownTrueCount) {
        val known = st.nextToken().toInt()
        union(knownKing, known)
    }

    repeat(M) { i ->
        st = StringTokenizer(br.readLine())
        val count = st.nextToken().toInt()
        repeat(count) {
            val partyMember = st.nextToken().toInt()
            if (myPartyListMap.containsKey(partyMember)) myPartyListMap[partyMember]!!.add(i)
            else myPartyListMap[partyMember] = mutableListOf(i)
            partyMap[i].add(partyMember)
        }
    }

    for (i in 0 until M) {
        speakTrueInParty(i)
    }

    for (i in 0 until M) {
        if (!visited[i]) speakLieCount++
    }

    print(speakLieCount)
}

fun speakTrueOtherParty(partyNum: Int) {
    if (visited[partyNum]) return
    visited[partyNum] = true
    for (member in partyMap[partyNum]) {
        union(knownKing, member)
        for (nextParty in myPartyListMap[member]!!)
            speakTrueOtherParty(nextParty)
    }
}

fun speakTrueInParty(partyNum: Int) {
    for (member in partyMap[partyNum]) {
        for (myParty in myPartyListMap[member]!!) {
            if (isSame(knownKing, member)) {
                speakTrueOtherParty(myParty)
            }
        }
    }
}

private fun isSame(a: Int, b: Int): Boolean {
    val pa = find(a)
    val pb = find(b)
    return parents[pa] == parents[pb]
}

private fun union(a: Int, b: Int) {
    val pa = find(a)
    val pb = find(b)
    if (pa != pb) {
        parents[parents[pb]] = parents[pa]
    }
}

private fun find(a: Int): Int {
    if (parents[a] == a) return a

    parents[a] = find(parents[a])
    return parents[a]
}