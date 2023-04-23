package 코틀린_코테스터디.week17

import java.lang.StringBuilder

/*
2
3
911
97625999
91125426
5
113
12340
123440
12345
98346
 */
class Solution5052 {
    companion object {
        private var T: Int = 0
        private var N: Int = 0
        private lateinit var tree: Array<Tree?>
        private var consistency: Boolean = false
        private val answer = StringBuilder()
    }

    fun solution() {
        T = readln().toInt()
        for (t in 1..T) {
            tree = Array(10) { null }
            testCase()
        }
        print(answer)
    }

    private fun testCase() {
        N = readln().toInt()
        consistency = true

        for (l in 1..N) {
            val line = readln()
            val nums = line.toCharArray().map { it - '0' }
            makeNumberBook(tree, 0, nums)
        }
        if (consistency) answer.append("YES\n") else answer.append("NO\n")
    }

    private fun makeNumberBook(tree: Array<Tree?>, cur: Int, nums: List<Int>) {
        val curNumber = nums[cur]
        // 현재 숫자가 처음이라면
        if (tree[curNumber] == null) {
            tree[curNumber] = Tree()

            // 내가 마지막이라면 종료 체크
            if (cur == nums.size - 1) {
                tree[curNumber]!!.end = true
                return
            }

            // 마지막이 아니고 끝난적 없으면, 다음 숫자트리 생성
            makeNumberBook(tree[curNumber]!!.childTree, cur + 1, nums)
        }
        // 처음이 아니라면
        else {
            // 내가 마지막이라면 종료
            if (cur == nums.size - 1) {
                tree[curNumber]!!.end = true
                consistency = false
                return
            }

            // 아직 종료가 체크되지 않았다면 이어서, 아니면 종료
            if (!tree[curNumber]!!.end) {
                makeNumberBook(tree[curNumber]!!.childTree, cur + 1, nums)
            } else {
                consistency = false
                return
            }
        }
    }

    private data class Tree(
        var end: Boolean = false
    ) {
        var childTree: Array<Tree?> = Array(10) { null }
    }
}

fun main() {
    Solution5052().solution()
}