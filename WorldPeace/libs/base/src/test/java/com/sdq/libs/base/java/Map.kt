package com.sdq.libs.base.java

import org.junit.Test

/**
 *
 * @PackageName com.sdq.libs.base.java
 * @date 2022/8/10 10:41
 * @author songdongqi
 */
class HashMap {
    val MAXIMUM_CAPACITY = 1 shl 30

    val DEFAULT_INITIAL_CAPACITY = 1 shl 4
    /**
     * 计算hash值
     * (h = key.hashCode()) ^ (h >>> 16)
     * 哈希值 异或 哈希值右移16位
     */
    fun hash(key: Any?): Int {
        var h: Int
        return if (key == null) 0 else key.hashCode().also { h = it } xor (h ushr 16)
    }

    private fun tableSizeFor(cap: Int): Int {
        var n = cap - 1
        n = n.or(n ushr 1)
        n = n.or(n ushr 2)
        n = n.or(n ushr 4)
        n = n.or(n ushr 8)
        n = n.or(n ushr 16)
        if (n < 0) return 1
        if (n >= MAXIMUM_CAPACITY) return MAXIMUM_CAPACITY
        return n + 1
    }

    @Test
    fun print(){
        println(DEFAULT_INITIAL_CAPACITY)
        println(MAXIMUM_CAPACITY)
        println(tableSizeFor(DEFAULT_INITIAL_CAPACITY))
        //下标
        //tab[i = (n - 1) & hash]
        println(16.and(hash("xx")))
    }
}