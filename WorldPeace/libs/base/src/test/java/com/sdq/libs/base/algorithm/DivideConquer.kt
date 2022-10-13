package com.sdq.libs.base.algorithm

import org.junit.Test

/**
 *
 * @PackageName com.sdq.libs.base.algorithm
 * @date 2022/10/13 10:01
 * @author songdongqi
 */
/**
 * 分治算法
 * 将原问题分解成一系列子问题
 * 递归地求解各个子问题，若子问题足够小，直接求解
 * 将子问题的结果合并成原问题
 */
class DivideConquer {
    private var num = 0

    private fun count(a: IntArray, n: Int): Int {
        num = 0
        mergeSortCounting(a, 0, n - 1)
        return num
    }

    private fun mergeSortCounting(a: IntArray, p: Int, r: Int) {
        if (p >= r) return
        val q = (p + r) / 2
        mergeSortCounting(a, p, q)
        mergeSortCounting(a, q + 1, r)
        merge(a, p, q, r)
    }

    private fun merge(a: IntArray, p: Int, q: Int, r: Int) {
        var i = p
        var j = q + 1
        var k = 0

        val tmp = IntArray(r - p + 1)
        while (i <= q && j <= r) {//
            println("${a[i]} , ${a[j]} , $q, $i")
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++]
            } else {
                println("++")
                num += (q - i + 1)//统计p-q之间，比a[j]大的元素个数
                tmp[k++] = a[j++]
            }
        }
        while (i <= q) {//处理剩下的
            tmp[k++] = a[i++]
        }
        while (j <= r) {//处理剩下的
            tmp[k++] = a[j++]
        }
        for (i in 0..(r - p)) {//从tmp拷贝回a
            a[p + i] = tmp[i]
        }
    }

    @Test
    fun test() {
        println(count(intArrayOf(2, 4, 3, 1, 5, 6), 6))
    }
}