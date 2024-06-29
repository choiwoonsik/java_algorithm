package leetCode

class MedianOfTwoSortedArrays {
    class Solution {
        lateinit var arr: IntArray

        fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
            arr = IntArray(nums1.size + nums2.size)

            var aIndex = 0
            var bIndex = 0
            var idx = 0
            while (true) {
                if (idx == arr.size) break

                if (aIndex > nums1.lastIndex) {
                    for (b in bIndex..nums2.lastIndex) {
                        arr[idx++] = nums2[b]
                    }
                } else if (bIndex > nums2.lastIndex) {
                    for (a in aIndex..nums1.lastIndex) {
                        arr[idx++] = nums1[a]
                    }
                } else {
                    val next =
                        if (nums1[aIndex] < nums2[bIndex]) nums1[aIndex++]
                        else nums2[bIndex++]
                    arr[idx++] = next
                }
            }

            return if (arr.size % 2 == 0) {
                val midFront = arr.size / 2 - 1
                val midBack = arr.size / 2
                (arr[midFront].toDouble() + arr[midBack].toDouble()) / 2.0
            } else {
                val mid = arr.size / 2
                arr[mid].toDouble()
            }
        }
    }
}