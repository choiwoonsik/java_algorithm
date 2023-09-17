package 힣_코틀린_코테스터디.토스_20

import kotlin.math.ceil

class Toss20Solution1 {
    fun solution(orderAmount: Long, taxFreeAmount: Long, serviceFee: Long): Long {
        // orderAmount : 주문금액
        // taxFreeAmount : 비과세금액
        // serviceFee : 봉사료
        val supplyAmount = orderAmount - serviceFee
        val taxTarget = supplyAmount - taxFreeAmount
        return if (taxTarget == 1L) 0 else calcTax(taxTarget)
    }

    private fun calcTax(money: Long): Long {
        val tax = ceil(money.toDouble() / 10).toLong()
        return tax
    }
}

fun main() {
    val ret = Toss20Solution1().solution(10000, 7817, 100)
    println(ret == 209L)
}