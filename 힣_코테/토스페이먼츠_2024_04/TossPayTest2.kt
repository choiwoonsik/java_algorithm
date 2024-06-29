package 힣_코테.토스페이먼츠_2024_04

/**
 * 길이가 3인 s의 substring을 10진수로 읽은 숫자이다.
 * 각 자리의 숫자가 모두 같다.
 */
class TossPayTest2 {
    fun solution(s: String): Int {
        if (s.length < 3) return -1
        var maxNice = 0

        for (i in 0 until s.length - 2) {
            if (s[i] == s[i + 1] && s[i] == s[i + 2]) {
                val nice = s.substring(i, i + 3).toInt()
                maxNice = maxOf(maxNice, nice)
            }
        }

        return maxNice
    }
}