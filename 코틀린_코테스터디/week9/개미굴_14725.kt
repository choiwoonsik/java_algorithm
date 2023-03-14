package 코틀린_코테스터디.week9

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import java.util.function.Function


class 개미굴_14725 {
    private var N = 0
    private var root: Trie = Trie()
    private var answer = StringBuilder()

    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st: StringTokenizer
        st = StringTokenizer(br.readLine())
        N = st.nextToken().toInt()
        root = Trie()

        for (i in 0 until N) {
            st = StringTokenizer(br.readLine())
            val len = st.nextToken().toInt()
            val words = mutableListOf<String>()
            for (j in 0 until len) {
                words[j] = st.nextToken()
            }
            makeTrie(words)
        }
        makeVisualization(-1, root)
        print(answer)
    }

    private fun makeVisualization(depth: Int, cur: Trie) {
        cur.child.sortBy { it.name }
        if (depth != -1) {
            for (i in 0 until depth) {
                answer.append("--")
            }
            answer.append(cur.name).append("\n")
        }
        for (child in cur.child) {
            makeVisualization(depth + 1, child)
        }
    }

    private fun makeTrie(words: List<String>) {
        var cur = root
        for (name in words) {
            var idx = -1
            if (cur.child.isNotEmpty()) {
                for (i in cur.child.indices) {
                    if (cur.child[i].name == name) {
                        idx = i
                        break
                    }
                }
            }
            if (idx == -1) {
                val child = Trie()
                child.name = name
                cur.child.add(child)
                cur = cur.child[cur.child.size - 1]
            } else {
                cur = cur.child[idx]
            }
        }
    }

    private class Trie {
        var name: String = ""
        var child: MutableList<Trie> = mutableListOf()
    }
}

fun main() {
    val problem = 개미굴_14725()
    problem.solution()
}