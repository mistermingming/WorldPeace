package com.sdq.libs.base.algorithm.leetcode

import com.sdq.libs.base.algorithm.BinaryNode

/**
 *
 * @PackageName com.sdq.libs.base.algorithm.leetcode
 * @date 2022/11/14 15:50
 * @author songdongqi
 */
class SymmetricalBinary101 {
    /**
     *
     */
    private fun isRootEqual(root1: BinaryNode?, root2: BinaryNode?): Boolean {
        return when {
            root1 == null && root2 == null -> true
            root1 == null || root2 == null || root1.value != root2.value -> false
            else -> isRootEqual(root1.left, root2.right) && isRootEqual(root1.right, root2.left)
        }
    }
}