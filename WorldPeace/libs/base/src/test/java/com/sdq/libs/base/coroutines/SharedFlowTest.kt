package com.sdq.libs.base.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

class SharedFlowTest {

    @Test
    fun testFlow() = runBlocking {
        val time = measureTimeMillis {
//        equeneFlow() //同步 1秒左右
            asyncFlow() //异步700多毫秒


        }
        print("cost $time")
    }

    private suspend fun asyncFlow() {
        channelFlow {
            for (i in 1..5) {
                delay(100)
                send(i)
            }
        }.collect {
            delay(100)
            println(it)
        }
    }

    //同步的
    private suspend fun equeneFlow() {
        flow<Int> {
            for (i in 1..5) {
                delay(100)
                emit(i)
            }
        }.collect {
            delay(100)
            println(it)
        }
    }

    @Test
    fun testFlow01() = runBlocking {
        val flow = flow<Int> {
            emit(1)
        }

        GlobalScope.launch {
            flow.collect {
                println("job1 $it")
            }
        }
        GlobalScope.launch {
            flow.collect {
                println("job2 $it")
            }
        }

        delay(1000)
    }

    @Test
    fun testSharedFlow() {
        //SharedFlow没有粘性事件
        var v = 0
        val stateFlow = MutableStateFlow(1)
        MutableSharedFlow<Int>()
        val sharedFlow1 = stateFlow.asSharedFlow()//MutableSharedFlow<Int>()
        runBlocking {
//            sharedFlow.emit(++v)
            val sharedFlow = stateFlow
                .shareIn(GlobalScope,SharingStarted.Lazily)

            val job = sharedFlow.onEach {
                println("receiver$it")
            }.launchIn(this)
//            sharedFlow.emit(++v)

            delay(5000)
            job.cancel()
            delay(1000)
            val job2 = sharedFlow.onEach {
                println("receiver2$it")
            }.launchIn(this)

            delay(5000)
//            sharedFlow.emit(++v)
            println("end")
        }
    }

    @Test
    fun testShareFlow01() {
        val sharedFlow = MutableSharedFlow<Int>()
        val value = AtomicInteger(0)
        runBlocking {
            GlobalScope.launch {
                sharedFlow.collect {
                    println("receiver value $it")
                }
            }
            GlobalScope.launch {
                delay(3000)
                sharedFlow.collect {
                    println("receiver2 value $it")
                }
            }
            while (true) {
                delay(1000)
                sharedFlow.emit(value.incrementAndGet())
            }
        }
    }

    @Test
    fun testShareFlow02(){
        //haredFlow默认是要等到订阅者全部接收到并且处理完成之后，才会进行下一次发送，否则就是挂起。
        var value = 0
        val sharedFlow = MutableSharedFlow<Int>()
        runBlocking {
            sharedFlow.onEach {
                delay(3000)
                println("receiver1:$it")
            }.launchIn(GlobalScope)
            sharedFlow.onEach {
                delay(1000)
                println("receiver2:$it")
            }.launchIn(GlobalScope)
            while (true) {
                val v = ++value
                println("send:$v")
                sharedFlow.emit(v)
                delay(1000)
            }
        }
    }

    @Test
    fun testShareFlow03(){
        var value = 0
        val sharedFlow = MutableSharedFlow<Int>(replay = 2, extraBufferCapacity = 3)
        runBlocking {
            sharedFlow.onEach {
                //模拟处理耗时
                delay(5000)
                println("receiver$it")
            }.launchIn(GlobalScope)

            GlobalScope.launch {
                delay(5000)
                sharedFlow.collect{
                    println("receiver2:$it")
                }
            }

            while (true) {
                delay(1000)
                val v = ++value
                sharedFlow.emit(v)
                println("send$v")
            }
        }
    }
    @Test
    fun testStateFlow() {
        //粘性事件
        val value = AtomicInteger(0)
        val stateFlow = MutableStateFlow(value.incrementAndGet()) //+1
        stateFlow.value = value.incrementAndGet()// +1
        runBlocking {
            GlobalScope.launch(Dispatchers.Default) {
                stateFlow.collect {
                    println("receiver value $it")
                }
            }
            GlobalScope.launch {
                delay(2000)
                stateFlow.collect {
                    println("receiver2 value $it")
                }
            }
            while (true) {
                delay(1000)
                val sendValue = value.incrementAndGet()
                println("sendValue$sendValue")
                stateFlow.emit(sendValue)
            }
        }
    }

    @Test
    fun testStateFlow01() {
        //丢失值问题
        val value = AtomicInteger(0)
        val stateFlow = MutableStateFlow(value.get())
        runBlocking {
            stateFlow.onEach {
                //模拟处理耗时
                delay(3000)
                println("receiver:$it")
            }.launchIn(this)
            while (true) {
                delay(1000)
                val v = value.incrementAndGet()
                println("send:$v")
                stateFlow.emit(v)
            }
        }

        //if (oldState == newState) return true StateFlow默认是防抖的
    }

    @Test
    fun testTransformFlow() {
        val sharedFlow = MutableSharedFlow<Int>()
        sharedFlow.asSharedFlow()

        val stateFlow = MutableStateFlow(0)
        stateFlow.asSharedFlow()
    }
}