package com.sdq.libs.base.designpattern.action11

/**
 *
 * @PackageName com.sdq.libs.base.designpattern.action11
 * @date 2022/8/23 13:44
 * @author songdongqi
 */
/**
 * 策略模式
 * Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy
 * lets the algorithm vary independently from clients that use it.
 */

/**
 * 定义
 */
interface Strategy {
    fun algorithmInterface()
}

class ConcreteStrategyA : Strategy {
    override fun algorithmInterface() {
        //具体的算法...
    }
}

class ConcreteStrategyB : Strategy {
    override fun algorithmInterface() {
        //具体的算法...
    }
}

/**
 * 创建
 */
object StrategyFactory {
    private val strategies: MutableMap<String, Strategy> = HashMap()

    fun getStrategy(type: String?): Strategy? {
        require(!(type == null || type.isEmpty())) { "type should not be empty." }
        return strategies[type]
    }

    init {
        strategies["A"] = ConcreteStrategyA()
        strategies["B"] = ConcreteStrategyB()
    }
}

/**
 * 使用
 */
class AlgorithmService {
    fun algorithm(type: String) {
        val algorithm: Strategy = StrategyFactory.getStrategy(type) ?: return
        algorithm.algorithmInterface()
    }
}