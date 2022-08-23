package com.sdq.libs.base.designpattern.action11

import org.junit.Test

/**
 *
 * @PackageName com.sdq.libs.base.designpattern.action18
 * @date 2022/8/5 13:15
 * @author songdongqi
 */
/**
 * 解释器模式
 *
 */

interface Expression {
    fun interpreter(stats: Map<String, Long>): Boolean
}

class AlertRuleInterpreter {
    private lateinit var expression: Expression

    constructor(ruleExpression: String) {

    }

    fun interpreter(stats: Map<String, Long>): Boolean {
        return true
    }
}

class Test {
    @Test
    fun test() {
        val rule = "key1 > 100 && key2 < 30 || key3 <100 || key4 == 88"
        val interpreter = AlertRuleInterpreter(rule)
        val stats = mapOf<String, Long>(
            "key1" to 101,
            "key2" to 121,
            "key3" to 88
        )
        val alert = interpreter.interpreter(stats)
        println("$alert")
    }
}