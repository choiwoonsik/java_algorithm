package 힣_코테.마카롱팩토리_2024_06

class Sol3 {
    companion object {
        private const val MAX_PIXEL = 1024
        private const val ZERO = 0
    }

    fun solution(S1: String, S2: String): Int {

        val rootS1 = Node(codes = S1)
        buildTree(rootS1, MAX_PIXEL, S1, 0)

        val rootS2 = Node(codes = S2)
        buildTree(rootS2, MAX_PIXEL, S2, 0)

        println("root1: ${rootS1.totalPixel()}")
        println("root2: ${rootS2.totalPixel()}")

        return sumTree(rootS1, rootS2)
    }

    private enum class POS {
        UR, UL, DL, DR
    }

    private data class Node(
        var pixel: Int = ZERO,
        var childNodeMap: MutableMap<POS, Node>? = mutableMapOf(),
        val codes: String,
    ) {
        fun isLeaf(): Boolean = childNodeMap == null || childNodeMap!!.isEmpty()
        fun totalPixel(): Int {
            return if (isLeaf()) pixel
            else childNodeMap!!.values.sumOf { it.totalPixel() }
        }
    }

    private fun sumTree(leftTree: Node?, rightTree: Node?): Int {
        if (leftTree == null && rightTree == null) return ZERO
        if (leftTree == null) return rightTree!!.totalPixel()
        if (rightTree == null) return leftTree.totalPixel()

        return if (leftTree.isLeaf() && rightTree.isLeaf()) {
            leftTree.totalPixel() + rightTree.totalPixel()
        } else if (leftTree.isLeaf().not() && rightTree.isLeaf()) {
            if (rightTree.pixel == ZERO) leftTree.totalPixel()
            else rightTree.totalPixel()
        } else if (leftTree.isLeaf() && rightTree.isLeaf().not()) {
            if (leftTree.pixel == ZERO) rightTree.totalPixel()
            else leftTree.totalPixel()
        } else {
            POS.values().sumOf { sumTree(leftTree.childNodeMap!![it], rightTree.childNodeMap!![it]) }
        }
    }

    private fun buildTree(current: Node, curPixel: Int, codes: String, codeIdx: Int = 0): Int {
        var index = codeIdx
        if (codes[index] == 'p') {
            current.childNodeMap = mutableMapOf()
            for (pos in POS.values())
                current.childNodeMap!![pos] = Node(ZERO, mutableMapOf(), codes)
            var totalJump = 0
            for (it in 0 until 4) {
                val pos = POS.values()[it]
                val jump = buildTree(current.childNodeMap!![pos]!!, curPixel / 4, codes, index + 1)
                index += jump
                totalJump += jump
            }

            return 1 + totalJump
        } else {
            current.pixel = if (codes[index] == 'w') ZERO else curPixel
            return 1
        }
    }
}

fun main() {
    val sol = Sol3()
    val solution = sol.solution("ppwwwbpbbwwbw", "pwbwpwwbw")
    println(solution)

    val solution1 = sol.solution("ppbbbwbww", "pwwbpwwwb")
    println(solution1)
}