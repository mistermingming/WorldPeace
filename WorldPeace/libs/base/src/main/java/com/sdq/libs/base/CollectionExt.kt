package com.sdq.libs.base

import kotlin.reflect.KProperty

/**
 *
 * @PackageName com.sdq.libs.base
 * @date 2022/12/13 14:17
 * @author songdongqi
 */
fun <T> Collection<T>.print(mapper: (T) -> String) =
    StringBuilder("\n[").also { stringBuilder ->
        this.forEach { e -> stringBuilder.append("\n\t${mapper(e)},") }
        stringBuilder.append("\n]")
    }.toString()

fun <K, V> Map<K, V?>.print(mapper: (V?) -> String):String =
    StringBuilder("\n{").also { stringBuilder ->
        this.iterator().forEach { e ->
            stringBuilder.append("\n\t[${e.key}] = ${mapper(e.value)}")
        }
        stringBuilder.append("\n}")
    }.toString()

fun Any.ofMap() =
    this::class.takeIf { it.isData }
        ?.members
        ?.filterIsInstance<KProperty<Any>>()
        ?.associate { it.name to it.call(this) }