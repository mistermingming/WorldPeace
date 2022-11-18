package com.sdq.libs.base.algorithm.leetcode

import org.junit.Test

/**
 *
 * @PackageName com.sdq.libs.base.algorithm.leetcode
 * @date 2022/11/18 15:52
 * @author songdongqi
 */
class RepeatValue217 {
    @Test
    fun test() {
        println(hasRepeatValue(intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)))
        println(hasRepeatValue(intArrayOf(1, 2, 3, 4)))
    }

    private fun hasRepeatValue(array: IntArray): Boolean {
        val hashSet = hashSetOf<Int>()
        array.forEach {
            if (!hashSet.add(it)) {
                return true
            }
        }
        return false
    }
}