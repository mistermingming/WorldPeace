package com.sdq.libs.base.algorithm.leetcode

import com.sdq.libs.base.algorithm.BinaryNode

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class SameTree100 {
    private fun isSameTree(p: BinaryNode?, q: BinaryNode?): Boolean {
        return when {
            p == null && q == null -> true
            p == null || q == null || p.value != q.value -> false
            else -> isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
        }
    }
}