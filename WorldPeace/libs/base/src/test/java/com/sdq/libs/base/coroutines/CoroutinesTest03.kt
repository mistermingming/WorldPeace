package com.sdq.libs.base.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.Test
import java.io.IOException
import java.lang.ArithmeticException
import java.lang.IllegalArgumentException
import java.lang.IndexOutOfBoundsException
import kotlin.coroutines.ContinuationInterceptor

class CoroutinesTest03 {
    @Test
    fun testCoroutineContext() = runBlocking<Unit> {
        launch(Dispatchers.Default + CoroutineName("test")) {
            println("i am working in thread ${Thread.currentThread().name}")
        }
    }

    @Test
    fun testCoroutineContextExtend() = runBlocking<Unit> {
        val scope = CoroutineScope(Job() + Dispatchers.IO + CoroutineName("test"))
        val job = scope.launch {
            println("${coroutineContext[Job]}  ${Thread.currentThread().name}")
            val result = async {
                println("${coroutineContext[Job]}  ${Thread.currentThread().name}")
                "OK"
            }.await()
        }
        job.join()
    }

    @Test
    fun testCoroutineContextExtend2() = runBlocking<Unit> {
        val coroutineExceptionHandler =
            CoroutineExceptionHandler { _, exception ->
                println("Caught $exception")

            }
        val scope = CoroutineScope(Job() + Dispatchers.Default + coroutineExceptionHandler)
        scope.launch {
            println("${coroutineContext[ContinuationInterceptor]} ,name: ${Thread.currentThread().name}")
        }.join()

        scope.launch(Dispatchers.IO) {
            println("${coroutineContext[ContinuationInterceptor]} ,name:  ${Thread.currentThread().name}")
        }.join()
    }

    @Test
    fun testExceptionPropagation() = runBlocking<Unit> {
        val job = GlobalScope.launch {
            try {
                throw IndexOutOfBoundsException()
            } catch (e: Exception) {
                println("caught IndexOutOfBoundsException")
            }
        }
        job.join()
        println("job finished.")
        val deferred = GlobalScope.async {
            println("deferred start")
            throw ArithmeticException()
        }
        deferred.await()
    }

    @Test
    fun testExceptionPropagation2() = runBlocking {
        val scope = CoroutineScope(Job())
        val job = scope.launch {
            async {
                throw IllegalArgumentException()
            }
        }
        job.join()
    }

    @Test
    fun testSupervisorJob() = runBlocking {
        val supervisor = CoroutineScope(SupervisorJob())
        val job1 = supervisor.launch {
            delay(100)
            println("child 1")
            throw IllegalArgumentException()
        }
        val job2 = supervisor.launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("job 2 finished.")
            }
        }
        joinAll(job1, job2)
    }

    @Test
    fun testSupervisorScope() = runBlocking {
        supervisorScope {

        }
    }

    @Test
    fun testCancelAndException() = runBlocking {
        val job = launch {
            val child = launch {
                println("child thread ${Thread.currentThread().name}")
                try {
                    delay(Long.MAX_VALUE)
                } catch (e: Exception) {
                    println("catch exception")
                } finally {
                    println("child is cancelled")
                }
            }
            yield()
            println("cancelling child ${Thread.currentThread().name}")
            child.cancelAndJoin()
//            yield()
            println("parent is not cancelled")
        }
        job.join()
    }

    @Test
    fun testExceptionAggregation() = runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception ${exception.suppressed.contentToString()}")
        }
        val job = GlobalScope.launch(handler) {
            launch {
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    throw ArithmeticException()
                }
            }
            launch {
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    throw IllegalArgumentException()
                }
            }
            launch {
                delay(100)
                throw IOException()
            }
            println("global launch finished.")
        }
        job.join()
    }

    @Test
    fun testSafeConcurrentTools() = runBlocking {
        var count = 0
        val mutex = Mutex()
        List(1000) {
            GlobalScope.launch {
                mutex.withLock {
                    count++
                }
            }
        }.joinAll()
        println(count)
    }

    @Test
    fun testScopeOrJonCancel() = runBlocking {
        val scope = CoroutineScope(Job())
        val job = scope.launch {
            println(" job 1 ${coroutineContext[Job]}")
            /*scope.*/launch {
            println("job 2 ${coroutineContext[Job]}")
            try {
                delay(20000)
                println("inner launch start ....")
            } catch (e: Exception) {
                println("exception :$e")
            }
            println("inner launch end.")
        }
        }
        delay(1000)
//        scope.cancel()
        println("$job")
        job.cancel()
        delay(2000)
    }
}