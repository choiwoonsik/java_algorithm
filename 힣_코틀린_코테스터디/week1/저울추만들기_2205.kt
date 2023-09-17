package 힣_코틀린_코테스터디.week1

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

private var N: Int = 0
private lateinit var visited: Array<Boolean>
private val sb = StringBuilder()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val maxPower = 2.0.pow(14).toInt()
    val list = mutableListOf<Int>()
    N = br.readLine().toInt()
    visited = Array(maxPower + 1) { false }

    repeat(N) { n ->
        val i = N - n
        var j = maxPower

        while (j > 1) {
            if (j - i <= N && !visited[j - i]) {
                visited[j - i] = true
                list.add(j - i)
                break
            }
            j /= 2
        }
    }

    list.reversed().map { sb.append("$it\n") }
    print(sb)
}