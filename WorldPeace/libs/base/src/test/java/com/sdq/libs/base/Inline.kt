package com.sdq.libs.base

/**
 * inline 就是我们常说的内联。
 * 这个关键字会在编译期间起作用。编译器会在编译的时候，把这个函数复制到调用处。
 *
 * 好处有三个：
 * 第一点，会减少函数调用的次数。我们知道，虽然函数调用的开销很小，但是确实是有一定的开销的。
 * 尤其是在大量的循环中，这种开销会变得更加明显。
 * 第二点，会减少对象的生成。
 */
fun testLambda() {
    val methodName = "main"
    multiplyByTwo(5) { result: Int -> println("call method $methodName, Result is: $result") }
}

/**
 * lambda表达式是如何执行的
 */
fun multiplyByTwo(
    num: Int,
    lambda: (result: Int) -> Unit
): Int {
    val result = num * 2
    lambda.invoke(result)
    return result
}

inline fun multiplyByTwo01(
    num: Int,
    lambda: (result: Int) -> Unit
): Int {
    val result = num * 2
    lambda.invoke(result)
    return result
}

/**
 * crossinline 保留了 inline 特性，但是如果想在传入的 lambda 里面 return 的话，就会报错。
 * return 只能 return 当前的这个 lambda。
 */
inline fun multiplyByTwo02(
    num: Int,
    lambda: (result: Int) -> Unit,
    noinline lambda1: (result: Int) -> Unit,
    crossinline lambda2: (result: Int) -> Unit
): Int {
    val result = num * 2
    lambda.invoke(result)
    return result
}