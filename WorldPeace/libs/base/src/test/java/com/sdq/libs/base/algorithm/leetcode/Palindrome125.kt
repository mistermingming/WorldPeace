package com.sdq.libs.base.algorithm.leetcode

import org.junit.Test

/**
 *
 * @PackageName com.sdq.libs.base.algorithm
 * @date 2022/7/21 9:02
 * @author songdongqi
 */
class Palindrome125 {
    private fun isPalindrome(s: String): Boolean {
        val filter = s.filter {
            it.isLetterOrDigit()
        }.lowercase()
        val reversed = filter.reversed()
        return filter == reversed
    }

    private fun isPalindrome01(s: String): Boolean {
        var left = 0
        var right = s.length - 1

        val array = s.toCharArray()
        while (left < right) {
            if (!array[left].isLetterOrDigit()) {
                ++left
                continue
            }
            if (!array[right].isLetterOrDigit()) {
                --right
                continue
            }
            if (array[left].lowercase() != array[right].lowercase()) {
                return false
            }
            ++left
            --right
        }
        return true
    }

    @Test
    fun testPalindrome() {
        val auth01 = isPalindrome01("race a car")
        val auth02 = isPalindrome01("a man ,a plan, a canal:Panama")
        println("$auth01 $auth02")
    }
}