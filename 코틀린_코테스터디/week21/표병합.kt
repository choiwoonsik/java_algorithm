package 코틀린_코테스터디.week21

class Solution표병합 {
    companion object {
        private lateinit var parent: IntArray
        private lateinit var map: Array<Array<String>>
        private lateinit var answer: MutableList<String>
    }

    private enum class Command {
        UPDATE, MERGE, UNMERGE, PRINT;

        companion object {
            fun find(command: String): Command {
                return Command.values().first { it.name == command }
            }
        }
    }

    private data class Dot(
        val y: Int,
        val x: Int,
    )

    fun solution(commands: Array<String>): Array<String> {
        map = Array(51) { Array(51) { "" } }
        answer = mutableListOf()
        parent = IntArray(2501)

        for (i in 0..50) {
            parent[i] = i
        }

        for (command in commands) {
            val line = command.split(" ")
            if (line.size == 5) {
                val r1 = line[1].toInt()
                val c1 = line[2].toInt()
                val r2 = line[3].toInt()
                val c2 = line[4].toInt()
                val dotA = Dot(r1, c1)
                val dotB = Dot(r2, c2)
                merge(dotA, dotB)
            } else if (line.size == 4) {
                val r1 = line[1].toInt()
                val c1 = line[2].toInt()
                val alterValue = line[3]
                update(Dot(r1, c1), alterValue)
            } else if (line.size == 3) {
                val com = Command.find(line[0])

                if (com == Command.UPDATE) {
                    val targetValue = line[1]
                    val alterValue = line[2]
                    update(targetValue, alterValue)
                } else if (com == Command.UNMERGE) {
                    val r1 = line[1].toInt()
                    val c1 = line[2].toInt()
                    unmerge(Dot(r1, c1))
                } else if (com == Command.PRINT) {
                    val r1 = line[1].toInt()
                    val c1 = line[2].toInt()
                    val parentDot = getParentDot(Dot(r1, c1))
                    answer.add(map[parentDot.y][parentDot.x])
                }
            }
        }

        return answer.toTypedArray()
    }

    private fun getParentDot(dot: Dot): Dot {
        val idx = convertDotToIdx(dot)
        val parentIdx = find(idx)
        return convertIdxToDot(parentIdx)
    }

    private fun unmerge(dot: Dot) {
        val pd = getParentDot(dot)
        val value = map[pd.y][pd.x]

        val idx = convertDotToIdx(dot)
        for (i in 0..50) {
            if (parent[i] == idx) {
                parent[i] = i
            }
        }

        map[dot.y][dot.x] = value
    }

    private fun update(dot: Dot, alterValue: String) {
        map[dot.y][dot.x] = alterValue
    }

    private fun update(targetValue: String, alterValue: String) {
        for (y in 1..50) {
            for (x in 1..50) {
                if (map[y][x] == targetValue) map[y][x] = alterValue
            }
        }
    }

    private fun merge(dotA: Dot, dotB: Dot) {
        val u = convertDotToIdx(dotA)
        val v = convertDotToIdx(dotB)

        val pu = find(u)
        val pv = find(v)

        union(pu, pv)
    }

    private fun union(pu: Int, pv: Int) {
        val u = find(pu)
        val v = find(pv)

        val dotA = convertIdxToDot(u)
        val dotB = convertIdxToDot(v)
        if (map[dotA.y][dotA.x] == "" && map[dotB.y][dotB.x] != "") {
            map[dotA.y][dotA.x] = map[dotB.y][dotB.x]
        } else if (map[dotA.y][dotA.x] != "" && map[dotB.y][dotB.x] == "") {
            map[dotB.y][dotB.x] = map[dotA.y][dotA.x]
        } else {
            map[dotA.y][dotA.x] = map[dotB.y][dotB.x]
        }

        parent[v] = parent[parent[u]]
    }

    private fun find(idx: Int): Int {
        if (parent[idx] == idx) return idx

        parent[idx] = find(parent[idx])
        return parent[idx]
    }

    private fun convertDotToIdx(dot: Dot): Int {
        return (dot.y - 1) * 50 + dot.x
    }

    private fun convertIdxToDot(idx: Int): Dot {
        if (idx <= 50) return Dot(1, idx)

        val m = idx / 50
        val n = idx - (m * 50)

        return Dot(m + 1, n)
    }
}

fun main() {
//    Solution표병합().solution()
}