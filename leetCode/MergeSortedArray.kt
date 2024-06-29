package leetCode

import java.util.Arrays

class MergeSortedArray {
    class Solution {
        fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
            var jdx = 0
            for (i in nums1.indices) {
                if (i < m) continue
                if (nums1[i] == 0) {
                    nums1[i] = nums2[jdx++]
                }
            }
            Arrays.sort(nums1)
        }
    }
}