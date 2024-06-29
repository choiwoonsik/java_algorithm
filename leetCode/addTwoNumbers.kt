package leetCode

import java.util.LinkedList

private class Solution {
    /**
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     * class ListNode(var `val`: Int) {
     *     var next: ListNode? = null
     * }
     */
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val queue = LinkedList<Int>()
        var link = ListNode(0)

        val head = link
        var linkLeft = l1
        var linkRight = l2

        while (true) {
            if (linkLeft != null && linkRight != null) {
                val beforeOverNumber = getOverNumber(queue)
                val sum = linkLeft.`val` + linkRight.`val` + beforeOverNumber
                val sumResult = calcSum(sum, queue)

                link.`val` = sumResult
                if (hasNext(linkLeft, linkRight, queue)) {
                    link.next = ListNode(0)
                    link = link.next!!
                }
                linkLeft = linkLeft.next
                linkRight = linkRight.next
            } else if (linkLeft != null) {
                val beforeOverNumber = getOverNumber(queue)
                val curSumNumber = linkLeft.`val` + beforeOverNumber
                val sumResult = calcSum(curSumNumber, queue);

                link.`val` = sumResult
                if (hasNext(linkLeft, null, queue)) {
                    link.next = ListNode(0)
                    link = link.next!!
                }
                linkLeft = linkLeft.next
            } else if (linkRight != null) {
                val beforeOverNumber = getOverNumber(queue)
                val curSumNumber = linkRight.`val` + beforeOverNumber
                val sumResult = calcSum(curSumNumber, queue)

                link.`val` = sumResult
                if (hasNext(null, linkRight, queue)) {
                    link.next = ListNode(0)
                    link = link.next!!
                }
                linkRight = linkRight.next
            } else {
                if (queue.isNotEmpty()) {
                    link.`val` = queue.poll()
                    link.next = null
                }
                break
            }
        }

        return head
    }

    private fun calcSum(sum: Int, queue: LinkedList<Int>): Int {
        return if (sum >= 10) {
            queue.add(1)
            sum % 10
        } else sum
    }

    private fun getOverNumber(queue: LinkedList<Int>): Int {
        return if (queue.isNotEmpty()) queue.poll() else 0
    }

    private fun hasNext(left: ListNode?, right: ListNode?, queue: LinkedList<Int>): Boolean {
        return left?.next != null || right?.next != null || queue.isNotEmpty()
    }
}