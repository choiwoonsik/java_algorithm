package 분리집합_UnionFind

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

private var N: Int = 0
private var M: Int = 0
private val sb = StringBuilder()
private lateinit var parents: MutableMap<String, String>
private lateinit var countMap: MutableMap<String, Int>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    N = br.readLine().toInt()
    parents = mutableMapOf()
    countMap = mutableMapOf()

    repeat(N) {
        M = br.readLine().toInt()
        parents.clear()
        countMap.clear()
        repeat(M) {
            val st = StringTokenizer(br.readLine())
            val a = st.nextToken()
            val b = st.nextToken()
            if (!parents.containsKey(a)) {
                parents[a] = a
                countMap[a] = 1
            }
            if (!parents.containsKey(b)) {
                parents[b] = b
                countMap[b] = 1
            }
            sb.append(calc(a, b)).append("\n")
        }
    }
    print(sb)
}

private fun calc(nameA: String, nameB: String): Int {
    val aParent = find(nameA)
    val bParent = find(nameB)

    if (parents[aParent] != parents[bParent]) {
        union(aParent, bParent)
    }

    return countMap[bParent]!!
}

private fun find(name: String): String {
    if (parents[name] == name) return name

    parents[name] = find(parents[name]!!)
    return parents[name]!!
}

private fun union(nameA: String, nameB: String) {
    val pa = find(nameA)
    val pb = find(nameB)

    if (pa != pb) {
        countMap[pb] = countMap[pa]!! + countMap[pb]!!
        parents[parents[pa]!!] = parents[pb]!!
    }
}