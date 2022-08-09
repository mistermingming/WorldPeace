package com.sdq.libs.base.algorithm

import org.junit.Test
import java.util.*
import kotlin.math.pow

/**
 *
 * @PackageName com.sdq.libs.base.algorithm
 * @date 2022/7/14 13:46
 * @author songdongqi
 */
class Rain42 {
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

    fun trap(height: IntArray): Int {
        val size = height.size
        if(size < 3) return 0

        var sumArea = 0
        val stack = Stack<Int>()
        height.forEachIndexed { index, value ->
            while (stack.isNotEmpty() && height[stack.peek()] <= value) {
                if (stack.size >= 2) {
                    val p = stack.pop()
                    val left = stack.peek()
                    val width = index - left - 1
                    val height = value.coerceAtMost(height[left]) - height[p]
                    sumArea += width * height
                } else {
                    stack.pop()
                }
            }
            stack.push(index)
        }
        return sumArea
    }

    fun trap01(height: IntArray): Int {
        val size = height.size
        if(size < 3) {
            return 0
        }

        var left = 0
        var right = size - 1
        var leftMax = height[left]
        var rightMax = height[right]
        var value = 0
        while (left < right) {
            leftMax = leftMax.coerceAtLeast(height[left])
            rightMax = rightMax.coerceAtLeast(height[right])
            if(leftMax < rightMax) {
                value += (leftMax - height[left])
                left++
            } else {
                value += (rightMax - height[right])
                right--
            }
        }

        return value
    }
}