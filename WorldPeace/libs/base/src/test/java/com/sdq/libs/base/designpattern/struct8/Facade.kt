package com.sdq.libs.base.designpattern.struct8

/**
 *
 * @PackageName com.sdq.libs.base.dp.struct8
 * @date 2022/7/29 10:20
 * @author songdongqi
 */
/**
 * 门面模式
 * 接口整合
 * 性能问题、分布式问题
 */
class Facade {
}

interface A {
    fun a()
}

interface B {
    fun b()
}

interface C {
    fun c()
}

interface Compose {
    fun a()
    fun b()
}