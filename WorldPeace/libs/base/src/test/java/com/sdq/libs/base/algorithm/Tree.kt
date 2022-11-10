package com.sdq.libs.base.algorithm

import org.junit.Test
import java.util.*

/**
 *
 * @PackageName com.sdq.libs.base.algorithm
 * @date 2022/7/18 13:15
 * @author songdongqi
 */
data class BinaryNode(
    val value: String,
    val left: BinaryNode?,
    val right: BinaryNode?
)

/**
 * L:左子树
 * D:根节点
 * R:右子树
 */
/**
 * 先序遍历
 * 根节点》左子树》右子树
 */
@Test
fun DLR(node: BinaryNode) {
    println(node.value)
    if (node.left != null) {
        DLR(node)
    }
    if (node.right != null) {
        DLR(node)
    }
}

/**
 * 后序遍历
 * 左子树-》右子树-》根节点
 */
@Test
fun LRD(node: BinaryNode) {
    if (node.left != null) {
        LRD(node)
    }
    if (node.right != null) {
        LRD(node)
    }
    println(node.value)
}

/**
 * 中序遍历
 * 左子树-》根节点-》右子树
 */
@Test
fun LDR(node: BinaryNode) {
    if (node.left != null) {
        LDR(node)
    }
    println(node.value)
    if (node.right != null) {
        LDR(node)
    }
}

/**
 * 深度优先遍历
 */
@Test
fun depthFirst(node: BinaryNode) {
    val stack = Stack<BinaryNode>()

    stack.push(node)

    while (stack.isNotEmpty()) {
        val root = stack.pop()

        println(root.value)

        if (root.right != null) {
            stack.push(root.right)
        }

        if (root.left != null) {
            stack.push(root.left)
        }
    }
}

/**
 * 广度优先遍历
 */
@Test
fun breadthFirst(node: BinaryNode) {
    val queue = ArrayDeque<BinaryNode>()

    queue.add(node)

    while (queue.isNotEmpty()) {
        val root = queue.poll() ?: return
        println(root.value)

        if (root.left != null) {
            queue.add(root.left)
        }

        if (root.right != null) {
            queue.add(root.right)
        }
    }
}
