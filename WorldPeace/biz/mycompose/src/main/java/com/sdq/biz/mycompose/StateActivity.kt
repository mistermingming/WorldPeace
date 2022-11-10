package com.sdq.biz.mycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable

/**
 *
 * @PackageName com.sdq.biz.mycompose
 * @date 2022/11/9 14:22
 * @author songdongqi
 */

/**
 * MutableState 类和 mutableStateOf() 函数用法和工作原理。了解到工作原理就好，这能让你对于「自动更新」
 * 有更清楚的认识，从而对于「什么时候会自动更新、怎样可以触发自动更新」具备预判的能力。
 * 另外对于 List、Map 这些集合类，它们也有对应的 mutableStateListOf()、mutableStateMapOf() 这样的函数。
 * 它们不能和普通的数据类型一样使用 mutableStateOf() 的原因和 Compose 的自动更新机制有关，如果有意往「高级」
 * 的方向突，可以了解一下，否则的话不看那么深也行。
 *
 * remember() 函数和 Compose 的重组作用域的了解。这是一个关于性能的知识点。
 *
 * Compose 的「无状态」的本质含义（是谁无状态？是界面组件的属性无状态，而不是界面组件无状态，这个要想明白），
 * 以及围绕它的「状态提升（State Hoisting）」和单向数据流这两个技术名词。这个懂了之后，就能写出可复用的组件了。
 *
 * CompositionLocal。这是 Compose 里的「针对 Composable 函数调用的、具有穿透能力的局部变量」，一般用来为嵌套
 * 调用的组件提供上下文信息。也是必备知识之一。
 *
 * 高级知识（如果对自己要求不高，可以先不深入了解）：
 * Compose 在重组过程中的性能风险，以及利用 @Stable 在特殊情况下进行性能优化；
 * derivedStateOf()：一个提供「状态链条」功能的函数，对于 A 状态的改变触发 B 状态的更新的场景适用，正确使用可以
 * 提高复杂场景下的性能。这个函数写起来很简单，关键是它的使用场景背后的机制要搞明白。可能会比较难想清楚，
 * 别着急，多想想。
 */
class StateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Composable
    fun Demo() {

    }
}