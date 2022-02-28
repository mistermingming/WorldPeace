package com.sdq.libs.base

import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select
import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.system.measureTimeMillis

class CoroutinesTest {

    @Test
    fun testMainScope() = runBlocking {
        println("start ${Thread.currentThread().name}")
        val startTime = System.currentTimeMillis()
        coroutineScope {

        }
        launch {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) {
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job : i am sleeping ${i++} ///")
                    nextPrintTime += 500
                }
            }
        }
        println("end ${Thread.currentThread().name}")
    }

    @Test
    fun testCoroutineBuilder() = runBlocking {
        val job = launch {
            delay(200)
            println("job finished.")
        }

        val deferred = async {
            delay(200)
            println("deferred finished.")
            "deferred result"
        }
        println(deferred.await())
    }

    @Test
    fun testCoroutineJoin() = runBlocking {
        val job1 = launch {
            delay(1000)
            println("one")
        }

        job1.join()

        val job2 = launch {
            delay(200)
            println("two")
        }

        val job3 = launch {
            delay(200)
            println("three")
        }
    }

    @Test
    fun testCoroutineAwait() = runBlocking {
        println("start ${Thread.currentThread().name}")
        val job1 = async {
            delay(1000)
            println("one")
        }

        job1.await()

        val job2 = async {
            delay(200)
            println("two")
        }

        val job3 = async {
            delay(200)
            println("three")
        }
    }

    @Test
    fun testCoroutineScope() = runBlocking {
        //挂起函数  一个协程失败了，所有其他兄弟协程也会被取消
        val deferred = coroutineScope {
            val job1 = launch {
                delay(400)
                println("job1 finished.")
            }

            async {
                delay(200)
                println("job2 finished.")
                "job2 result"
            }
        }
        println(deferred.await())
    }

    @Test
    fun testSupervisorScope() = runBlocking {
        //挂起函数  一个协程失败了，不会影响其他兄弟协程
        supervisorScope {
            val job1 = launch {
                delay(400)
                println("job1 finished.")
            }

            val job2 = async {
                delay(200)
                println("job2 finished.")
                throw IllegalArgumentException()
            }
        }
    }

    val runner = ControlledRunner<Unit>()

    @Test
    fun testCoroutineRunner() = runBlocking {
        val time = measureTimeMillis {
            coroutineScope {
                println("main ${Thread.currentThread().name}")
                for (i in 1..5) {
                    launch {
                        println("launch ${Thread.currentThread().name}")
                        runner.joinPreviousOrRun {
                            println("i = $i")
                            delay(3000)
                        }
                    }
                }
            }
        }
        println("$time ms")
    }

    @Test
    fun testCoroutineCycle(): Unit = runBlocking {
        for (i in 1..3) {
                launch {
                    runner.cancelPreviousThenRun {
                        delay(3000)
                        println("internal launch finished $i")
                    }
                }
                delay(1000)
                println("global scope launch finished")
        }
    }

    @Test
    fun testOnAwait()= runBlocking<Unit> {

        select<Int> {
            async {
                1
            }.onAwait

        }
        launch {

        }
    }
}