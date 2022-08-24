package com.sdq.libs.base.jvm

import java.lang.ref.ReferenceQueue
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference

/**
 *
 * @PackageName com.sdq.libs.base.jvm
 * @date 2022/8/24 15:38
 * @author songdongqi
 */
/**
 * 强引用(Strongly Reference) Object obj = new Object()
 * 软引用(Soft Reference)、软引用可用来实现内存敏感的高速缓存
 * 弱引用(Weak Reference)
 * 虚引用(Phantom Reference)
 */
class Reference {

    fun softReference() {
        var str = "abc"
        val softReference = SoftReference(str)

        val queue = ReferenceQueue<String>()
        val softReference1 = SoftReference(str, queue)

    }

    fun weakReference() {
        var s: String? = "Frank"
        val weakRef: WeakReference<String> = WeakReference<String>(s)
        s = null
        //弱可达:如果一个对象与GC Roots之间仅存在弱引用，则称这个对象为弱可达(weakly reachable)对象。
        //WeakReference类所提供的get方法会返回其引用对象的强引用
    }
}