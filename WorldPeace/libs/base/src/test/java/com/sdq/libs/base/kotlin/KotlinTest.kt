package com.sdq.libs.base.kotlin

import com.sdq.libs.base.Event
import org.junit.Test
import java.util.*
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

    @Test
    fun testEquals() {
        val trade = Trade(1)
        val new = Trade(id = 1)
        println(System.identityHashCode(trade))
        println(System.identityHashCode(new))
        println(trade == new)
        println(trade === new)
        println(Throw(2) == Throw(2))
    }

    @Test
    fun testAny() {
        var a = 1
        var b = 2
        a = b.also { b = a }

        println("$a , $b")
    }

    @Test
    fun testUUID() {
        println(UUID.nameUUIDFromBytes("9774d56d682e549c".toByteArray()))
        println(UUID.nameUUIDFromBytes("9774d56d682e549c".toByteArray()))
    }

    @Test
    fun initOrder() {
        val order = Order("22222")
    }
}

class Order {
    constructor() {

    }

    constructor(value: String) {

    }

    init {
        println("init ")
    }
}

class Throw(
    val id: Int
)

class Trade(
    val id: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Trade

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}

data class CopyEvent(
    val value: String,
    override val eventId: Long = Event.generateEventId()
) : Event

class Scope {
    companion object {
        private const val ACCESS: String = "access"
    }
}

class Test {
    fun test() {
        Scope
    }
}