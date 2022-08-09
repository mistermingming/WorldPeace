package com.sdq.libs.base.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.Test
import java.lang.RuntimeException
import java.util.concurrent.atomic.AtomicInteger

class FlowTest01 {
    @Test
    fun testFlowTransformOperator() = runBlocking {
        (1..3).asFlow()
            .onEach { }
            .map { }
            .collect {}

        (1..3).asFlow()
            .transform {
                emit("making request $it")
                emit("response $it")
            }
            .collect { println(it) }
    }

    @Test
    fun testLimitLengthOperator() = runBlocking {
        flow {
            try {
                emit(1)
                emit(2)
                println("this line will not execute")
                emit(3)
            } catch (e: Throwable) {
                println("exception $e")
            } finally {
                println("finally  in numbers")
            }
        }.take(2).collect { println(it) }
    }

    @Test
    fun testTerminalOperator() = runBlocking {
        val sum = (1..5).asFlow()
            .map { it * it }
            .reduce { a, b ->

                a + b
            }

    }

    @Test
    fun testZip() = runBlocking {
        val numbs = (1..3).asFlow()
        val strs = flowOf("one", "two", "three")
        numbs.zip(strs) { a, b -> "$a -> $b" }
            .collect { println(it) }
    }

    @Test
    fun testZip2() = runBlocking {
        val numbs = (1..3).asFlow().onEach { delay(300) }
        val strs = flowOf("one", "two", "three").onEach { delay(400) }
        val startTime = System.currentTimeMillis()
        numbs.zip(strs) { a, b -> "$a -> $b" }
            .collect { println("$it at ${System.currentTimeMillis() - startTime} ms from start") }
    }

    private fun requestFlow(i: Int) = flow<String> {
        emit("$i:First")
        delay(500)
        emit("$i:second")
    }

    @Test
    fun testFlowMapConcat() = runBlocking {
        val startTime = System.currentTimeMillis()
        (1..3).asFlow()
            .onEach { delay(100) }
//            .map { requestFlow(it) }
//            .flatMapConcat { requestFlow(it) }//连接模式
            .flatMapMerge { requestFlow(it) }
//            .flatMapLatest { requestFlow(it) }
            .collect {
                println("$it at ${System.currentTimeMillis() - startTime} ms from start")
            }
    }

    private fun simpleFlow() = flow<Int> {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i)
        }
    }
    @Test
    fun testCancellationException() = runBlocking<Unit> {
        val job1 = GlobalScope.launch {
            try {
                delay(1000)
                println("job 1")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        delay(100)
        job1.cancelAndJoin()
    }
    @Test
    fun testFlowException() = runBlocking<Unit> {
        try {
            simpleFlow().collect{
                println(it)
                check(it<=1){"collected $it"}
            }
        } catch (e: Exception) {
            println("catch $e")
        }
    }

    @Test
    fun testFlowException2() = runBlocking {
        flow{
            emit(2)
            throw ArithmeticException("div 0")
        }.catch { e:Throwable -> println("caught $e") }
            .flowOn(Dispatchers.IO)
            .collect{ println(it)}
    }

    private fun simpleFlow6() = flow<Int> {
        emit(3)
        emit(2)
        throw RuntimeException()
    }

    @Test
    fun testFlowInOnCompletion() = runBlocking {
        simpleFlow6()
            .onCompletion {exception->
                if (exception!=null) println("flow completed exception")
            }
            .collect{ println(it)}
    }
    @Test
    fun testTimerProduce() = runBlocking {

    }

    @Test
    fun testSelectFlow() = runBlocking<Unit> {
        listOf(simpleFlow(),simpleFlow())
            .merge()
            .collect{

            }

        AtomicInteger(0).incrementAndGet()
    }
}