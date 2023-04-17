package 코테.라인_2022_05

import java.util.*

class sol7 {
    fun solution() {
        val pq = PriorityQueue(Comparator { f1: Flower, f2: Flower ->
            if (f1.end != f2.end) return@Comparator f1.end.compareTo(f2.end)
            else return@Comparator Integer.compare(f1.start, f2.start)
        })

        val flowers = mutableListOf<Flower>()
        flowers.sortedWith(compareBy(
            { it.start },
            { it.end }
        ))
    }

    private data class Flower(
        val start: Int,
        val end: Int,
    )
}