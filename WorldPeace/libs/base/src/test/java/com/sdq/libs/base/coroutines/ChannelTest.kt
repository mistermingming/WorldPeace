package com.sdq.libs.base.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import org.junit.Test

class ChannelTest  {
    @Test
    fun testKnowChannel()= runBlocking{
        val channel = Channel<Int>()
        //
        val producer = GlobalScope.launch {
            var i = 0
            while (true){
                delay(1000)
                channel.send(++i)
                println("send $i")
            }
        }
            //
        val consumer = GlobalScope.launch {
            while (true){
                val element = channel.receive()
                println("receive $element")
            }
        }
        joinAll(producer,consumer)
    }

    @Test
    fun testKnowChannel2()= runBlocking{
        val channel = Channel<Int>()
        //
        val producer = GlobalScope.launch {
            var i = 0
            while (true){
                delay(1000)
                channel.send(++i)
                println("send $i")
            }
        }
        //
        val consumer = GlobalScope.launch {
            while (true){
                delay(2000)
                val element = channel.receive()
                println("receive $element")
            }
        }
        joinAll(producer,consumer)
    }

    @Test
    fun testIterateChannel()= runBlocking{
        val channel = Channel<Int>(Channel.UNLIMITED)
        //
        val producer = GlobalScope.launch {
            for (x in 1..5){
                channel.send(x*x)
                println("send ${x*x}")
            }
        }
        //
        val consumer = GlobalScope.launch {
//            val iterator = channel.iterator()
//            while (iterator.hasNext()){
//                val element = iterator.next()
//                println("receive $element")
//                delay(2000)
//            }

            for (element in channel){
                println("receive $element")
                delay(2000)
            }
        }
        joinAll(producer,consumer)
    }

    @Test
    fun testFastProducerChannel()  = runBlocking {
        val receiveChannel:ReceiveChannel<Int> = GlobalScope.produce {
            repeat(10){
                delay(1000)
                send(it)
            }
        }
        val consumer = GlobalScope.launch {
            for (i in receiveChannel){
                println("received $i")
            }
        }
    }

    @Test
    fun testCloseChannel() = runBlocking {
        val channel = Channel<Int>(3)
        val producer = GlobalScope.launch {
            List(3){
                channel.send(it)
                println("send $it")
            }
            channel.close()
        }

        val consumer = GlobalScope.launch {

        }
        joinAll(producer,consumer)
    }
}