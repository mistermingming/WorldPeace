package com.sdq.libs.base.algorithm

import org.junit.Test

/**
 *
 * @PackageName com.sdq.libs.base.algorithm
 * @date 2022/10/12 9:14
 * @author songdongqi
 */
/**
 * 回溯算法
 */
class BackTracking {
    var maxW = Integer.MIN_VALUE//结果放到maxW中
    private val weight = intArrayOf(2, 2, 4, 6, 3)//物品重量
    private val n = 5//物品个数
    private val w = 9//背包承受的最大重量

    private var mem = Array(5) { BooleanArray(10) }

    @Test
    fun test() {
        backTracking(0, 0)
        println(maxW)
    }

    /**
     * i:将要决策第几个物品是否装入背包
     * cw：当前背包中物品的总重量
     */
    private fun backTracking(i: Int, cw: Int) {//调用（0，0）
        if (cw == w || i == n) {//cw == w表示装满了，i==n表示物品都考察完了
            if (cw > maxW) maxW = cw
            return
        }

        if (mem[i][cw]) return //重复状态
        mem[i][cw] = true

        backTracking(i + 1, cw)//选择不装第i个物品
        if (cw + weight[i] <= w) {
            backTracking(i + 1, cw + weight[i])//选择装第i个物品
        }
    }
}