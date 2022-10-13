package com.sdq.libs.base.algorithm

/**
 *
 * @PackageName com.sdq.libs.base.algorithm
 * @date 2022/10/12 10:17
 * @author songdongqi
 */
/**
 * 递归
 * 电影院第几排，f(n) = f(n-1)+1,其中f(1)=1
 * 写出递推公式，找到终止条件
 */
class Recursion {

    /**
     * @param n, 台阶数量，每次可以跨1个或者2个台阶
     * f(1) = 1
     * f(2) = 2
     * f(n) = f(n-1) + f(n-2)
     * @return 走法数量
     */
    private fun climbStairs(n: Int): Int {
        if (n == 1) return 1
        if (n == 2) return 2
        return climbStairs(n - 1) + climbStairs(n - 2)
    }

    /**
     * tips:
     * 1、堆栈溢出
     * 2、重复计算-散列表
     */
}