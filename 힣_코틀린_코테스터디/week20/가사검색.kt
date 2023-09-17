package 힣_코틀린_코테스터디.week20

class Solution가사검색 {
    companion object {
        private var memoization: HashMap<String, Int> = HashMap()
        private var root: Node = Node(mutableListOf())
    }

    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        for (word in words) {
            makeWordTree(root, word, 0)
        }

        val answer = IntArray(queries.size)
        for ((i, query) in queries.withIndex()) {
            if (query.first() == '?' && query.last() == '?') answer[i] = root.words.count { it.length == query.length }
            else if (memoization[query] == null) {
                answer[i] = countWord(root, query, 0)
                memoization[query] = answer[i]
            }
            else answer[i] = memoization[query]!!
        }

        return answer
    }

    private fun countWord(node: Node, query: String, idx: Int): Int {
        if (idx >= query.length) return 0
        if (node.child == null) return 0

        val queryChar = query[idx]
        val queryIdx = queryChar - 'a'

        if (query[idx] == '?') {
            if (idx == 0 && query.last() == '?') {
                return node.words.count { it.length == query.length }
            }

            if (idx == query.length - 1) {
                return node.words.count { it.length == query.length }
            }
            return node.child!!.filterNotNull().sumOf { countWord(it, query, idx + 1) }
        }

        if (idx == query.length - 1) {
            return node.words.count { it[idx] == queryChar && it.length == query.length }
        }

        return if (node.child!![queryIdx] == null) 0
        else countWord(node.child!![queryIdx]!!, query, idx + 1)
    }

    private fun makeWordTree(node: Node, word: String, idx: Int) {
        if (idx >= word.length) return

        node.words.add(word)

        if (node.child == null) {
            node.child = Array(26) { null }
        }

        val i = word[idx] - 'a'

        if (node.child!![i] == null) node.child!![i] = Node(mutableListOf())
        makeWordTree(node.child!![i]!!, word, idx + 1)
    }

    private data class Node(
        val words: MutableList<String>
    ) {
        var child: Array<Node?>? = null
    }
}

fun main() {
    val answer = Solution가사검색().solution(
        words = arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao"),
        queries = arrayOf("fro??", "????o", "fr???", "fro???", "pro?")
    )
    answer.map { println(it) }
}