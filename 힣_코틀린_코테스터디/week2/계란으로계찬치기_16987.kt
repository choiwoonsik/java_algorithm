package 힣_코틀린_코테스터디.week2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.max
import java.util.StringTokenizer

/*
8
222 117
176 92
287 6
284 81
221 96
263 96
188 40
225 1
 */

private var N: Int = 0
private lateinit var eggs: Array<Egg>

private data class Egg(
    var hp: Int = 0,
    val atk: Int = 0,
)

private var max: Int = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    N = br.readLine().toInt()

    eggs = Array(N) { Egg() }
    repeat(N) { i ->
        val st = StringTokenizer(br.readLine())
        val hp = st.nextToken().toInt()
        val atk = st.nextToken().toInt()
        eggs[i] = Egg(hp, atk)
    }

    attack(0)
    print(max)
}

private fun attack(cur: Int) {
    if (cur == N) {
        var cnt = 0
        for (i in 0 until N) {
            if (eggs[i].hp <= 0) cnt++
        }
        max = max(max, cnt)
        return
    }

    if (eggs[cur].hp <= 0) {
        attack(cur + 1)
        return
    }

    for (next in 0 until N) {
        if (eggs[next].hp <= 0) continue
        if (next == cur) continue
        eggs[next].hp -= eggs[cur].atk
        eggs[cur].hp -= eggs[next].atk
        attack(cur + 1)
        eggs[next].hp += eggs[cur].atk
        eggs[cur].hp += eggs[next].atk
    }
    if (cur == N - 1) attack(cur + 1)
}
