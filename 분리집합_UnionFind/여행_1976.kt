package 분리집합_UnionFind

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
3
3
0 1 0
1 0 1
0 1 0
1 2 3
 */
private var N: Int = 0
private var M: Int = 0
private lateinit var board: Array<Array<Int>>
private lateinit var parents: Array<Int>

fun main() {
    var br = BufferedReader(InputStreamReader(System.`in`))
    var st: StringTokenizer
    N = br.readLine().toInt()
    M = br.readLine().toInt()
    board = Array(N + 1) { Array(N + 1) { 0 } }
    parents = Array(N + 1) {0}
    repeat(N + 1) { i -> parents[i] = i}

    repeat(N) { y ->
        st = StringTokenizer(br.readLine())
        repeat(N) { x ->
            val next = st.nextToken().toInt()
            val i = y + 1
            val j = x + 1
            if (next == 1) union(i, j)
        }
    }

    st = StringTokenizer(br.readLine())
    var start = st.nextToken().toInt()
    while (st.hasMoreTokens()) {
        val next = st.nextToken().toInt()
        if (isSame(start, next)) start = next
        else {
            print("NO")
            return
        }
    }

    print("YES")
}

private fun isSame(a: Int, b: Int): Boolean {
    val pa = find(a)
    val pb = find(b)
    return parents[pa] == parents[pb]
}

private fun find(a: Int): Int {
    if (parents[a] == a) return a

    parents[a] = find(parents[a])
    return parents[a]
}

private fun union(a: Int, b: Int) {
    val pa = find(a)
    val pb = find(b)
    if (pa != pb) parents[pa] = pb
}