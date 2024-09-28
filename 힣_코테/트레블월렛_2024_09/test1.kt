package 힣_코테.트레블월렛_2024_09

import java.util.PriorityQueue

fun main() {
    val s = '1' - '0'
    println(s)
    println(s::class.java)

    val i = IntArray(1)
    val mu = mutableSetOf<Int>()
    mu.addAll(i.toSet())



    PriorityQueue<Dto>(compareBy { it.a })
    mu.minOf { it }
    arrayOf(1, 2, 3).sort()
}

data class Dto(
    val a: Int,
    val b: Int
)

sealed class A {
    val name: String = "A"
    data object B : A()
    data object C : A()
}

fun test(a: A) {
    when (a) {
        is A.B -> println("B ${a.name}")
        is A.C -> println("C ${a.name}")
    }
}