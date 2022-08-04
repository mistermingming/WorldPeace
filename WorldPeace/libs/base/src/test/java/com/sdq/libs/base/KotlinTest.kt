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

    @Test
    fun tableSize() {
        var n = 16 - 1
        n = n.or(n.ushr(1))
        n = n.or(n.ushr(2))
        n = n.or(n.ushr(4))
        n = n.or(n.ushr(8))
        n = n.or(n.ushr(16))
        val result = if (n < 0) 1 else n + 1
        println("result = $result")
    }

    @Test
    fun midTest() {
        val left = 0
        val right = 10
        val mid = (left + right).ushr(1)
        println("mid = $mid")
    }


    @Test
    fun testEvent() {
        val event = CopyEvent("2")
        println("event = ${event.value} ${event.eventId}")
        val copy = event.copy(value = "3", eventId = Event.generateEventId())
        println("copy event = ${copy.value} ${copy.eventId}")
    }
}

data class CopyEvent(
    val value :String,
    override val eventId: Long = Event.generateEventId()
) : Event