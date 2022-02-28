package com.sdq.libs.base

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.Test
import kotlin.system.measureTimeMillis

class FlowTest {
    private fun simpleList(): List<Int> = listOf(1, 2, 3)

    private fun simpleSequence(): Sequence<Int> = sequence {
        for (i in 1..3) {
            Thread.sleep(1000)//block
//            delay()
            yield(i)
        }
    }

    private suspend fun simpleList2(): List<Int> {
        delay(1000)
        return listOf(1, 2, 3)
    }

    @Test
    fun testMultipleValues() {
        simpleList().forEach {
            println("$it")
        }
    }

    @Test
    fun testMultipleValues2(): Unit = runBlocking {
        simpleList().forEach {
            println("$it")
        }
    }

    //返回多个值，异步
    private fun simpleFlow() = flow {
        for (i in 1..3) {
            delay(1000)
            emit(i)//发射
        }
    }

    private suspend fun test() {
        coroutineScope {

        }

        supervisorScope {

        }
    }

    @Test
    fun testMultipleValues3() = runBlocking {
        launch {
            for (k in 1..3) {
                println("i am not block3d $k")
                delay(1300)
            }
        }
        simpleFlow().collect {
            println("$it")
        }
        println("main finished.")
    }

    private fun simpleFlow2() = flow {
        println("flow started")
        for (i in 1..3) {
            delay(1000)
            emit(i)
        }
    }

    //冷流，直到流被收集的时候才会执行
    @Test
    fun testFlowIsCold() = runBlocking {
        val flow = simpleFlow2()
        println("calling collect.....")
        flow.collect { println("$it") }
        println("calling collect again")
        flow.collect { println("$it") }
    }

    @Test
    fun testFlowContinuation() = runBlocking {
        (1..5).asFlow().filter {
            it % 2 == 0
        }.map { "string $it" }
            .collect {
                println("collect $it")
            }
    }

    @Test
    fun testFlowBuilder() = runBlocking {
        flowOf("one", "two", "three")
            .onEach { delay(1000) }
            .collect { println(it) }
    }

    private fun simpleFlow3() = flow {
        println("flow started ${Thread.currentThread().name}")
        for (i in 1..3) {
            delay(1000)
            emit(i)
        }
    }.flowOn(Dispatchers.IO)

    @Test
    fun testFlowContext() = runBlocking {
        println("main : ${Thread.currentThread().name}")
        simpleFlow3().collect {
            println("collect $it ${Thread.currentThread().name}")
        }
    }

    private fun events() = (1..3)
        .asFlow()
        .onEach { delay(100) }
        .flowOn(Dispatchers.Default)

    @Test
    fun testFlowLaunch() = runBlocking {
        val job = events()
            .onEach { println("Event: $it ${Thread.currentThread().name}") }
            .launchIn(CoroutineScope(Dispatchers.IO))
        job.cancelAndJoin()
        job.join()
    }

    private fun simpleFlow4() = flow<Int> {
        for (i in 1..3) {
            delay(1000)
            println("emitting $i")
            emit(i)
        }
    }

    /**
     * 流构建器对每个发射值执行附加的ensureactive检测以进行取消
     * 从flow{}发出的繁忙循环是可以取消的
     * 出于性能原因，大多数其他流操作不会自动执行其他取消检测
     * 通过cancellable
     */
    @Test
    fun testCancelFlow(): Unit = runBlocking {
        withTimeoutOrNull(2500) {
            simpleFlow4()
                .collect { println(it) }
            println("Done")
        }

        (1..5).asFlow()
            .onEach { println("asFlow emit $it") }
            .cancellable()
            .collect {
                println(it)
                if (it == 3) cancel()
            }
    }

    private fun simpleFlow5() = flow<Int> {
        for (i in 1..3) {
            delay(100)
            emit(i)
            println("emitting $i ${Thread.currentThread().name}")
        }
    }

    @Test
    fun testFlowBackPressure() = runBlocking {
        val time = measureTimeMillis {
            simpleFlow5()
//                .flowOn()
//                .buffer(50)//缓存，背压
//                .conflate()
                .collectLatest {
                    println("collect $it ${Thread.currentThread().name}")
                    delay(300)
                    println("collect $it end")
                }
//                .collect {
//                    println("collect $it ${Thread.currentThread().name}")
//                    delay(300)
//                    println("collect $it end")
//                }
        }
        println(time)
    }
}