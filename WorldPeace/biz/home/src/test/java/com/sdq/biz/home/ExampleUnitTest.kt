package com.sdq.biz.home

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testReified(){
        val test  = notice<A<stock>,stock>()?.extra?.name?:"null"
        println("id = $test")
    }

    val notice:Notice = A(1,fund(2))

    inline fun <reified T : Notice,reified E> notice():T?{
        return if (notice !is T) null else notice
//        return if (notice !is T||(notice is A<*> && notice.extra !is E)) null else notice
    }
}
data class stock(val name:Int)

data class fund(val id :Int)

abstract class Notice(val id: Int)

class A<E>(
     id: Int,
     val extra:E
): Notice(id)

class B(
    id: Int
): Notice(id)