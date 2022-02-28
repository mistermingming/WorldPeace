package com.sdq.libs.base

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.selects.select
import org.junit.Test

class ChannelTest01 {
    @Test
    fun testSelectChannel() = runBlocking {
        val channels = listOf(Channel<Int>(), Channel<Int>())

        GlobalScope.launch {
            delay(100)
            channels[0].send(200)
        }

        GlobalScope.launch {
            delay(50)
            channels[1].send(100)
        }

        val result = select<Int> {
            channels.forEach {
                it.onReceive
            }
        }
        println(result)
    }

    @Test
    fun testSelectClause2() = runBlocking {
        val channels = listOf(Channel<Int>(), Channel<Int>())
        println(channels)

        launch(Dispatchers.IO) {
            select<Unit?> {
                launch {
                    delay(10)
                    channels[1].onSend(200){
                        println("sent on $it")
                    }
                }
                launch{
                    delay(100)
                    channels[0].onSend(100){
                        println("sent on $it")
                    }
                }
            }
        }

        GlobalScope.launch {
            println(channels[0].receive())
        }
        GlobalScope.launch {
            println(channels[1].receive())
        }
        delay(1000)
    }

    @Test
    fun testSelectFlow() = runBlocking {

    }
}