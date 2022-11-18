package com.sdq.libs.base.algorithm.leetcode

import org.junit.Test

/**
 *
 * @PackageName com.sdq.libs.base.algorithm.leetcode
 * @date 2022/11/18 14:13
 * @author songdongqi
 */
class DisplayOne136 {
    @Test
    fun test() {
        println(pickDisplayOneTimeNum01(intArrayOf(4, 1, 2, 3, 1, 2, 3)))
    }

    private fun pickDisplayOneTimeNum(array: IntArray): Int {
        val map = hashMapOf<Int, Int>()
        array.forEach {
            val value = map.getOrDefault(it, 0)
            map[it] = value + 1
        }
        map.forEach {
            if (it.value == 1) {
                return it.key
            }
        }
        return -1
    }

    /**
     * 异或
     */
    private fun pickDisplayOneTimeNum01(array: IntArray): Int {
        var value = array[0]
        if (array.size > 1) {
            for (i in 1 until array.size) {
                value = value xor array[i]
            }
        }
        return value
    }
}