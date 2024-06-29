package 힣_코테.야놀자_2024_02

class Test1 {
    fun changeAds(base10: Int): Int {
        val binary = toBinary(base10)
        val convertedBinary = convertBit(binary)
        return toBase10(convertedBinary)
    }

    private fun convertBit(binary: String): String {
        val result = StringBuilder()
        for (i in binary.indices) {
            if (binary[i] == '0') {
                result.append('1')
            } else {
                result.append('0')
            }
        }
        return result.toString()
    }

    private fun toBase10(convertedBinary: String): Int {
        return Integer.parseInt(convertedBinary, 2)
    }

    private fun toBinary(base10: Int): String {
        return base10.toString(2)
    }
}

fun main() {
    val changeAds = Test1().changeAds(50)
    println(changeAds)
}