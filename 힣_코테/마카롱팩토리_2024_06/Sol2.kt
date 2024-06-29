package 힣_코테.마카롱팩토리_2024_06

/*
1부터 자연수를 이어서 나열
- 1234567891011121314151617181920212223...

이때 n번째 숫자를 구한다
- n: 1
- return: 1

- n: 11
- return: 0

- n: 21
- return: 5

n <= 1_000_000_000
 */
/*
1 ~ 9
10 ~ 99
100 ~ 999

123456789
10/11/12/13/14

14를 원한다
14 - 9  = 5
index = 4 // 5 - 1
5 / 2 = 2
5 % 2 = 1

99(189)
100/101/102

193 - 189 = 4
4 / 3 = 1
4 % 3 = 1
193을 원한다

총길이 - 원하는 위치 = 뒤에서 위치
 */
class Sol2 {
    companion object {
        private var totalNumberLength = 0L
        private var curNumber = 0L
    }

    fun solution(n: Int): Int {
        makeBefore(n, 9, 1, 9L, 9L)

        val startNumber = ++curNumber
        val startNumberLength = curNumber.toString().length
        val targetNumberIndex = n - totalNumberLength
        val targetNumber = startNumber + (targetNumberIndex - 1) / startNumberLength
        val targetNumberSingleIndex = (targetNumber % startNumberLength).toInt()

        return targetNumber.toString()[targetNumberSingleIndex].code
    }

    private fun makeBefore(
        n: Int,
        indexNumber: Int,
        depth: Int = 1,
        beforeNumber: Long,
        beforeTotalLength: Long
    ) {
        if (n <= beforeTotalLength) return

        curNumber = beforeNumber
        totalNumberLength = beforeTotalLength

        println("depth: $depth, curNumber: $curNumber, totalNumberLength: $totalNumberLength")

        return makeBefore(
            n,
            indexNumber * 10,
            depth + 1,
            beforeNumber + indexNumber * 10,
            beforeTotalLength + (indexNumber * 10 * (depth + 1))
        )
    }
}