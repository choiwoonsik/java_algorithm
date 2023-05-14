package 코틀린_코테스터디.week19

import java.util.StringTokenizer
import kotlin.math.max

/*
3
8 5
1 100
3 5
 */
class Solution16987 {
    companion object {
        private var N: Int = 0
        private var max: Int = 0
        private lateinit var eggs: Array<Egg>
    }

    private data class Egg(
        var hp: Int,
        val atk: Int,
    )

    fun solution() {
        N = readln().toInt()
        eggs = Array(N) { Egg(0, 0) }
        for (i in 0 until N) {
            val l = StringTokenizer(readln())
            val hp = l.nextToken().toInt()
            val atk = l.nextToken().toInt()
            eggs[i] = Egg(hp, atk)
        }

        dfs(0)
        print(max)
    }

    private fun dfs(cur: Int) {
        if (cur == N) {
            max = max(max, eggs.count { it.hp <= 0 })
            return
        }

        if (eggs[cur].hp <= 0) {
            dfs(cur + 1)
            return
        }

        for (next in 0 until N) {
            if (next == cur) continue
            if (eggs[next].hp > 0) {
                eggs[cur].hp -= eggs[next].atk
                eggs[next].hp -= eggs[cur].atk
                dfs(cur + 1)
                eggs[cur].hp += eggs[next].atk
                eggs[next].hp += eggs[cur].atk
            }
        }
        if (cur == N - 1) dfs(cur + 1)
    }
}

fun main() {
    Solution16987().solution()
}