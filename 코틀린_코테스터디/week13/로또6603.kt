package 코틀린_코테스터디.week13

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.text.StringBuilder

class Problem6603 {
    private var len: Int = 0
    private lateinit var nums: IntArray
    private lateinit var visited: BooleanArray
    private val total = StringBuilder()

    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        while (true) {
            val st = StringTokenizer(br.readLine())
            val count = st.nextToken().toInt()
            if (count == 0) break

            len = count
            nums = IntArray(count)
            visited = BooleanArray(count)
            for (i in 0 until count) nums[i] = st.nextToken().toInt()
            nums.sort()

            recursive(0, 0)
            total.append("\n")
        }
        print(total)
    }

    private fun recursive(cur: Int, depth: Int) {
        if (depth == 6) {
            val answer = StringBuilder()
            for (i in 0 until len) {
                if (visited[i]) answer.append("${nums[i]} ")
            }
            total.append(answer.trim()).append("\n")
            return
        }

        for (idx in cur until len) {
            if (!visited[idx]) {
                visited[idx] = true
                recursive(idx + 1, depth + 1)
                visited[idx] = false
            }
        }
    }
}

fun main() {
    Problem6603().solution()
}