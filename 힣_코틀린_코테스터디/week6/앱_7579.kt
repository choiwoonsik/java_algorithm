import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


private var N = 0
private var M = 0
private var totalCost = 0
private lateinit var memory: IntArray
private lateinit var cost: IntArray
private lateinit var dp: Array<IntArray>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    memory = IntArray(N + 1)
    cost = IntArray(N + 1)

    st = StringTokenizer(br.readLine())
    for (i in 1..N) {
        memory[i] = st.nextToken().toInt()
    }

    st = StringTokenizer(br.readLine())
    for (i in 1..N) {
        cost[i] = st.nextToken().toInt()
        totalCost += cost[i]
    }
    dp = Array(N + 1) { IntArray(totalCost + 1) }

    for (app in 1..N) {
        val appCost = cost[app]
        val appMemory = memory[app]

        for (curCost in 0..totalCost) {
            dp[app][curCost] = dp[app - 1][curCost]

            if (curCost < appCost) continue
            if (dp[app][curCost] < dp[app - 1][curCost - appCost] + appMemory) {
                dp[app][curCost] = dp[app - 1][curCost - appCost] + appMemory
            }
        }
    }

    for (i in 1..totalCost) {
        if (dp[N][i] >= M) {
            println(i)
            break
        }
    }
}
