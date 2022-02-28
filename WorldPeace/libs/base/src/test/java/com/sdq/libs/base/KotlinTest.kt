package com.sdq.libs.base

import org.junit.Test
import kotlin.system.measureTimeMillis

class KotlinTest {

    @Test
    fun testArray() {
        measureTimeMillis {
            (0..100)
                .map {
                    println("map $it")
                    it + 1
                }
                .filter {
                    println("filter $it")
                    it % 2 == 0
                }
                .find {
                    println("find $it")
                    it > 3
                }
                .let {
                    println("result $it")
                }
        }.let { println("list time = $it") }


        measureTimeMillis {
            (0..100).asSequence()
                .map {
                    println("map $it")
                    it + 1
                }
                .filter {
                    println("filter $it")
                    it % 2 == 0
                }
                .find {
                    println("find $it")
                    it > 3
                }
                .let {
                    println("result $it")
                }
        }.let { println("sequence time = $it") }
    }
}