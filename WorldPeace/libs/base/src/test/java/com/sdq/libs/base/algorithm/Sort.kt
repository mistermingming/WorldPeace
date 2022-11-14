package com.sdq.libs.base.algorithm

/**
 *
 * @PackageName com.sdq.libs.base.algorithm
 * @date 2022/11/14 15:01
 * @author songdongqi
 */
class Sort {
    /**
     *
     */
    // 冒泡排序，a表示数组，n表示数组大小
    private fun bubbleSort(a: IntArray, n: Int) {
        if (n <= 1) return
        for (i in 0 until n) {
            // 提前退出冒泡循环的标志位
            var flag = false
            for (j in 0 until n - i - 1) {
                if (a[j] > a[j + 1]) { // 交换
                    val tmp = a[j]
                    a[j] = a[j + 1]
                    a[j + 1] = tmp
                    flag = true // 表示有数据交换
                }
            }
            if (!flag) break // 没有数据交换，提前退出
        }
    }

    /**
     *
     */
    // 插入排序，a表示数组，n表示数组大小
    private fun insertionSort(a: IntArray, n: Int) {
        if (n <= 1) return
        for (i in 1 until  n) {
            val value = a[i]
            var j = i - 1
            // 查找插入的位置
            while (j >= 0) {
                if (a[j] > value) {
                    a[j + 1] = a[j] // 数据移动
                } else {
                    break
                }
                --j
            }
            a[j + 1] = value // 插入数据
        }
    }
}