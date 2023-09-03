package 코테.넥슨_2023_08

lateinit var partyMap: Array<IntArray>

fun fizzBuzz(n: Int): Unit {
    // Write your code here

    partyMap = Array(n) { IntArray(n) }

    for (i in 0 until n) {
        partyMap[i][0] = 5
    }
}

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()

    fizzBuzz(n)
}
