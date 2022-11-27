package 분리집합_UnionFind

import java.io.BufferedReader
import java.io.InputStreamReader

/*
4
3
4
1
1
 */
private var N: Int = 0
private var M: Int = 0
private lateinit var parents: IntArray
private var max = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    N = br.readLine().toInt()
    M = br.readLine().toInt()

    parents = IntArray(N + 1)
    repeat(N + 1) { i ->
        parents[i] = i
    }
    repeat(M) {
        val port = br.readLine().toInt()
        val parentPort = find(port)

        if (parentPort == 0) {
            print(max)
            return
        }

        union(parentPort - 1, parentPort)
        max++
    }
    print(max)
}

private fun union(aPort: Int, bPort: Int) {
    val ap = find(aPort)
    val bp = find(bPort)
    if (ap != bp) {
        parents[bp] = ap
    }
}

private fun find(port: Int): Int {
    if (parents[port] == port) return port
    parents[port] = find(parents[port])
    return parents[port]
}