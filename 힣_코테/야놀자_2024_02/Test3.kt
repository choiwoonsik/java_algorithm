package 힣_코테.야놀자_2024_02

private class Test3 {

    fun isValid(a: Array<Int>): String {
        val tree = makeTree(a)
        val preOrder = tree.preOrderTraversal().trim()

        val map = preOrder
            .split(" ")
            .map { it.toInt() }
            .toIntArray()

        for ((index, value) in a.withIndex()) {
            if (value != map[index]) return "NO"
        }
        return "YES"
    }

    private fun makeTree(a: Array<Int>): Node {
        val root = Node(a[0])
        for (i in 1 until a.size) {
            root.insert(a[i])
        }

        return root
    }

    private data class Node(
        val value: Int,
        var left: Node? = null,
        var right: Node? = null
    ) {
        fun insert(inputValue: Int) {
            if (inputValue < this.value) {
                if (left == null) {
                    left = Node(inputValue)
                } else {
                    left!!.insert(inputValue)
                }
            } else {
                if (right == null) {
                    right = Node(inputValue)
                } else {
                    right!!.insert(inputValue)
                }
            }
        }

        fun preOrderTraversal(): String {
            val result = StringBuilder()
            result.append("$value ")
            if (left != null) result.append(left!!.preOrderTraversal())
            if (right != null) result.append(right!!.preOrderTraversal())

            return result.toString()
        }
    }
}

fun main() {
    val valid = Test3().isValid(arrayOf(10, 7, 5, 4, 1, 2, 3, 6, 9, 8, 11, 12))
}