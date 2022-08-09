package com.sdq.libs.base.coroutines

import kotlinx.coroutines.*
import org.junit.Test

class CoroutinesTest01 {
    @Test
    fun testCoroutineCancel() = runBlocking<Unit> {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            try {
                delay(1000)
                println("job 1")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        scope.launch {
            delay(1000)
            println("job 2")
        }

        scope.cancel()
        delay(1000)
    }

    @Test
    fun testBrotherCancel() = runBlocking<Unit> {
        val scope = CoroutineScope(Dispatchers.Default)
        val job1 = scope.launch {
            delay(1000)
            println("job 1")
        }

        val job2 = scope.launch {
            delay(1000)
            println("job 2")
        }
        delay(100)
        job1.cancel()
        delay(1000)
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
    fun testCancelCpuTaskByIsActive() = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i  = 0
            while (i<5 && isActive){
                if (System.currentTimeMillis() >= nextPrintTime){
                    println("job : i am sleeping ${i++} ///")
                    nextPrintTime += 500
                }
            }
        }
//        delay(1300)
        println("main i am tired of waiting!")
        job.cancelAndJoin()
        println("main : now i can quit.")
    }

    @Test
    fun testCancelCpuTaskByEnsureActive() = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i  = 0
            while (i<5){
                ensureActive()//抛出异常
                if (System.currentTimeMillis() >= nextPrintTime){
                    println("job : i am sleeping ${i++} ///")
                    nextPrintTime += 500
                }
            }
        }
//        delay(1300)
        println("main i am tired of waiting!")
        job.cancelAndJoin()
        println("main : now i can quit.")
    }

    @Test
    fun testCancelCpuTaskByYield() = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i  = 0
            while (i<5){
                yield()
                if (System.currentTimeMillis() >= nextPrintTime){
                    println("job : i am sleeping ${i++} ///")
                    nextPrintTime += 500
                }
            }
        }
        delay(1300)
        println("main i am tired of waiting!")
        job.cancelAndJoin()
        println("main : now i can quit.")
    }
}