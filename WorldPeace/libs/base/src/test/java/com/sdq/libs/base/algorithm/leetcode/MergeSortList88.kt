package com.sdq.libs.base.algorithm.leetcode

/**
 *
 * @PackageName com.sdq.libs.base.algorithm.leetcode
 * @date 2022/11/14 15:34
 * @author songdongqi
 */
class MergeSortList88 {
    fun merge(
        array1: IntArray,
        num1: Int,
        array2: IntArray,
        num2: Int
    ) {
        val mergeArray = IntArray(num1 + num2)
        var index1 = 0
        var index2 = 0
        var index = 0
        while (index1 < num1 || index2 < num2) {
            mergeArray[index++] = when{
                (index1 == num1) -> array2[index2++]
                index2==num2 -> array1[index1++]
                array1[index1]<array2[index2] -> array1[index1++]
                else -> array2[index2++]
            }
        }
    }
}