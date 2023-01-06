package com.sdq.libs.base.kotlin

/**
 *
 * @PackageName com.sdq.libs.base.kotlin
 * @date 2022/9/6 16:38
 * @author songdongqi
 */
fun test() {

}

object ExtensionFun {
    fun test1() {

    }
}

class Extension {
    private val value = "value"
    private val valueGet: String
        get() {
            return "value"
        }

    private fun testValue() {
        valueGet
        value
    }

    companion object {
        fun test2() {

        }
    }
}

class Value {
    var change: Int = 2
    val noChange: Int = 1
}