package 코틀린_코테스터디.토스_20

class Toss20Solution2 {
    fun solution(amountText: String): Boolean {
        val last = amountText.length - 1
        val arr = amountText.toCharArray()

        // 0원자리부터 맨앞으로 탐색
        var commaCount = 0
        var mustComma = false
        for (i in last downTo 0) {
            val c = arr[i]
            ++commaCount

            if (i == last) {
                // 0원자리
                if ((c in '0'..'9').not()) return false
            } else if (i == 0) {
                // 맨앞자리
                if ((c in '1'..'9').not()) return false
            } else {
                // 중간자리
                if (i == last - 3) {
                    // 첫번째 콤마 체크
                    if (commaCount == 4 && c == ',') mustComma = true
                }
                if (commaCount == 4 && c == ',') {
                    // 콤마는 3번의 수 이후만
                    if (!mustComma) return false
                    commaCount = 0
                } else {
                    // 그 외 숫자가 아니라면
                    if ((c in '0'..'9').not()) return false
                }
            }

        }
        return true
    }
}

fun main() {
    println("TRUE")
    println(Toss20Solution2().solution("1"))
    println(Toss20Solution2().solution("99000"))
    println(Toss20Solution2().solution("99,000"))
    println(Toss20Solution2().solution("999,000"))
    println(Toss20Solution2().solution("100,999,000"))

    println("FALSE")
    println(Toss20Solution2().solution("099,000"))
    println(Toss20Solution2().solution("0999,000"))
    println(Toss20Solution2().solution("0,999,000"))
    println(Toss20Solution2().solution(",999,000"))
    println(Toss20Solution2().solution("0100"))
    println(Toss20Solution2().solution("123,4567,89"))
    println(Toss20Solution2().solution("123,456,789,"))
    println(Toss20Solution2().solution("123,456,789만"))
}