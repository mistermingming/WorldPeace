package com.sdq.libs.base.designpattern.action11

/**
 *
 * @PackageName com.sdq.libs.base.designpattern.action18
 * @date 2022/8/5 10:27
 * @author songdongqi
 */
/**
 * 中介模式
 * 多对多网状关系转换为一对多星状关系
 * 中介模式解决了多个对象之间交互的问题，将多个对象的行为封装到一起，然后任意对象和这个中介交互，中介包含了具体
 * 的业务逻辑。
 */
interface Mediator {
    fun handleEvent(event: String)
}

class MediatorImpl {

}