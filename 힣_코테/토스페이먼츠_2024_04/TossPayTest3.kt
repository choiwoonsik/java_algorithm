package 힣_코테.토스페이먼츠_2024_04

/**
 * 금액은 0~9 사이의 숫자와 , 로만 구성되어야 한다.
 * 0원이 아닌 이상 0으로 시작할 수 없다.
 * 세자리 구분자 쉼표가 있거나, 없거나 둘중 하나이다.
 * 세자리 구분자는 오른쪽 숫자부터 3개의 숫자마다 1개의 구분자가 등장한다.
 * 쉼표는 맨앞이나 맨뒤에 없어야 한다.
 */
class TossPayTest3 {
    fun solution(amountText: String): Boolean {
        return ReceiptSystem(amountText).isOkReceipt()
    }

    private class ReceiptSystem(
        private val amountText: String
    ) {
        fun isOkReceipt(): Boolean {
            return if (!checkNumberAndComma()) false
            else if (!checkZeroNotStart()) false
            else if (!checkDivideComma()) false
            else true
        }

        private fun checkNumberAndComma(): Boolean {
            return amountText.all { it.isDigit() || it == ',' }
        }

        private fun checkZeroNotStart(): Boolean {
            return if (amountText.startsWith("0")) amountText.length == 1
            else true
        }

        private fun checkDivideComma(): Boolean {
            val divideCount = amountText.count { it == ',' }
            return if (divideCount == 0) true
            else {
                val countIsOk = amountText.length / 4 == divideCount
                countIsOk && checkDivideCommaPosition() && checkDivideCommaStartEnd()
            }
        }

        private fun checkDivideCommaPosition(): Boolean {
            return amountText.reversed().mapIndexed { index, comma ->
                if (comma == ',') (index + 1) % 4 == 0
                else true
            }.let { it.all { it } }
        }

        private fun checkDivideCommaStartEnd(): Boolean {
            return amountText.first() != ',' && amountText.last() != ','
        }
    }
}

fun main() {
    val solution = listOf(
        TossPayTest3().solution("0100"),
        TossPayTest3().solution("25,000,123"),
        TossPayTest3().solution("25,000,123,"),
        TossPayTest3().solution("25,000,123,0"),
        TossPayTest3().solution("2500012300"),
    )
    println(solution)
}