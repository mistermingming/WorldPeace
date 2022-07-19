package com.sdq.libs.base.algorithm

import org.junit.Test
import java.util.*

/**
 *
 * @PackageName com.sdq.libs.base.algorithm
 * @date 2022/7/14 13:46
 * @author songdongqi
 */
class One {
    private fun getRain(intArray: IntArray): Int {
        var sumArea = 0
        val stack = Stack<Int>()
        intArray.forEachIndexed { right, value ->
            while (stack.isNotEmpty() && intArray[stack.peek()] <= value) {
                if (stack.size >= 2) {
                    val p = stack.pop()
                    val left = stack.peek()
                    val width = right - left - 1
                    val height = value.coerceAtMost(intArray[left]) - intArray[p]
                    sumArea += width * height
                } else {
                    stack.pop()
                }
            }
            stack.push(right)
        }
        return sumArea
    }

    @Test
    fun testRain() {
        val rain = getRain(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1))
        println("$rain")
    }
}