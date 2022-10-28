package com.sdq.libs.base.algorithm

/**
 *
 * @PackageName com.sdq.libs.base.algorithm
 * @date 2022/8/29 10:10
 * @author songdongqi
 */
class Hash {
    /**
     * 散列函数设计的基本要求：
     * 散列函数计算得到的散列值是一个非负整数；
     * 如果 key1 = key2，那 hash(key1) == hash(key2)；
     * 如果 key1 ≠ key2，那 hash(key1) ≠ hash(key2)。-》无法完全避免这种散列冲突
     */

    /**
     * 散列冲突
     */

    /**
     * 1. 开放寻址法
     * 线性探测方法、步长是 1
     * 二次探测（Quadratic probing）步长“二次方”
     * 和双重散列（Double hashing）一组散列函数
     */

    /**
     * 2. 链表法
     */

    /**
     * 设计散列函数
     * 散列函数的设计不能太复杂；
     * 散列函数生成的值要尽可能随机并且均匀分布。
     *
     * 直接寻址法、平方取中法、折叠法、随机数法等
     * 将单词中每个字母的ASCll 码值“进位”相加，然后再跟散列表的大小求余、取模，作为散列值。
     * hash("nice")=(("n" - "a") * 26*26*26 + ("i" - "a")*26*26 + ("c" - "a")*26+ ("e"-"a")) / 78978
     */

    /**
     * 动态扩容、动态缩容
     * 当有新数据要插入时，我们将新数据插入新散列表中，并且从老的散列表中拿出一个数据放入到新散列表。每次插入
     * 一个数据到散列表，我们都重复上面的过程。经过多次插入操作之后，老的散列表中的数据就一点一点全部搬移到新
     * 散列表中了。这样没有了集中的一次性数据搬移，插入操作就都变得很快了。
     */

    /**
     * 总结：
     * 当数据量比较小、装载因子小的时候，适合采用开放寻址法。这也是 Java 中的ThreadLocalMap使用开放寻址法解
     * 决散列冲突的原因。
     * 基于链表的散列冲突处理方法比较适合存储大对象、大数据量的散列表，而且，比起开放寻址法，它更加灵活，
     * 支持更多的优化策略，比如用红黑树代替链表。
     */

    /**
     * 何为一个工业级的散列表？工业级的散列表应该具有哪些特性？
     *
     * 支持快速地查询、插入、删除操作；
     * 内存占用合理，不能浪费过多的内存空间；
     * 性能稳定，极端情况下，散列表的性能也不会退化到无法接受的情况。
     * 如何实现这样一个散列表呢？
     * 设计一个合适的散列函数；
     * 定义装载因子阈值，并且设计动态扩容策略；
     * 选择合适的散列冲突解决方法。
     */
}