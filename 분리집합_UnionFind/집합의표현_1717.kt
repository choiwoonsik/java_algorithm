package 분리집합_UnionFind

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1
 */

private var N: Int = 0
private var M: Int = 0
private var parents = intArrayOf()
private val sb = StringBuilder()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    parents = IntArray(N + 1)

    repeat(N + 1) { i ->
        parents[i] = i
    }

    repeat(M) {
        st = StringTokenizer(br.readLine())
        val type = st.nextToken().toInt()
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()

        when (type) {
            0 -> union(a, b)
            1 -> isSame(a, b)
        }
    }

    print(sb)
}

private fun union(a: Int, b: Int) {
    val aParent = find(a)
    val bParent = find(b)

    parents[bParent] = aParent
}

private fun isSame(a: Int, b: Int) {
    if (find(a) == find(b)) sb.append("YES\n")
    else sb.append("NO\n")
}

private fun find(a: Int): Int {
    if (parents[a] == a) {
        return a
    }

    parents[a] = find(parents[a])
    return parents[a]
}