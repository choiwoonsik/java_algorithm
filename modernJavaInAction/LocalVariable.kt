package modernJavaInAction

class LocalVariable {
    fun execute() {
        val immutableNumber = 5
        executeLambda { -> println(immutableNumber * 10) }
    }

    private fun executeLambda(
        multipleLambda: () -> Unit
    ) {
        multipleLambda()
    }
}

fun main() {
    LocalVariable().execute()
}