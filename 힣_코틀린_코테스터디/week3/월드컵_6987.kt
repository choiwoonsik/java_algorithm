package 힣_코틀린_코테스터디.week3

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

private lateinit var teams: Array<Game>
private lateinit var allMatchCase: Array<Pair<Int, Int>>
private const val N = 6
private var endGame = false

fun main() {
    var match = 0
    allMatchCase = Array(N * (N - 1) / 2) { 0 to 0 }
    for (myT in 0 until N) {
        for (youT in myT + 1 until N) {
            allMatchCase[match] = myT to youT
            match++
        }
    }

    teams = Array(N) { Game(0, 0, 0) }
    val sb = StringBuilder()
    val br = BufferedReader(InputStreamReader(System.`in`))
    repeat(4) {
        val st = StringTokenizer(br.readLine())
        var isOk = true
        for (t in 0 until N) {
            val w = st.nextToken().toInt()
            val d = st.nextToken().toInt()
            val l = st.nextToken().toInt()

            teams[t] = Game(w, d, l)
            if (teams[t].isInFive().not()) {
                isOk = false
            }
        }

        playGame(0)
        if (isOk.not()) sb.append("0 ")
        else {
            when (endGame) {
                true -> sb.append("1 ")
                false -> sb.append("0 ")
            }
        }
        endGame = false
    }

    print(sb)
}

fun playGame(match: Int) {
    if (endGame || match == allMatchCase.size) {
        endGame = true
        return
    }

    val myT = allMatchCase[match].first
    val youT = allMatchCase[match].second

    if (teams[myT].win > 0 && teams[youT].lose > 0) {
        teams[myT].win--
        teams[youT].lose--
        playGame(match + 1)
        teams[myT].win++
        teams[youT].lose++
    }
    if (teams[myT].draw > 0 && teams[youT].draw > 0) {
        teams[myT].draw--
        teams[youT].draw--
        playGame(match + 1)
        teams[myT].draw++
        teams[youT].draw++
    }
    if (teams[myT].lose > 0 && teams[youT].win > 0) {
        teams[myT].lose--
        teams[youT].win--
        playGame(match + 1)
        teams[myT].lose++
        teams[youT].win++
    }

}

private data class Game(
    var win: Int,
    var draw: Int,
    var lose: Int,
) {
    fun isInFive(): Boolean {
        return win + draw + lose <= 5
    }
}