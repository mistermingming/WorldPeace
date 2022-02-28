package com.sdq.libs.base

import kotlinx.coroutines.*
import org.junit.Test
import java.io.BufferedReader
import java.io.FileReader

class CoroutinesTest02 {
    @Test
    fun testReleaseResources() = runBlocking {
        val job = launch {
            repeat(1000) {
                try {
                    println("job : i am sleeping $it ....")
                } finally {
                    println("job i am running finally")
                }
            }
        }
        delay(1300)
        println("main i am tired of waiting!")
        job.cancelAndJoin()
        println("main now i can quit.")
    }

    @Test
    fun testUseFunction() = runBlocking {
        val br = BufferedReader(FileReader("D:\\I have a dream.txt"))
        with(br) {
            var line: String?
            use {
                while (true) {
                    line = readLine() ?: break;
                    println(line)
                }
            }
        }
    }

    @Test
    fun testCancelWithNonCancellable() = runBlocking {
        val job = launch {
            repeat(1000) {
                try {
                    println("job : i am sleeping $it ....")
                    delay(500)
                } finally {
                    //不能取消的任务
                    withContext(NonCancellable) {
                        println("job i am running finally")
                        delay(1000)
                        println("job and i have just delayed for 1 sec because i am non-cancellable")
                    }
                }
            }
        }
        delay(1300)
        println("main i am tired of waiting!")
        job.cancelAndJoin()
        println("main now i can quit.")
    }

    @Test
    fun testDealWithTimeOut() = runBlocking {
        withTimeout(1200) {
            repeat(1000) {
                println("job i am sleeping $it ...")
                delay(500)
            }
        }
    }

    @Test
    fun testDealWithTimeoutReturn() = runBlocking {
        val result = withTimeoutOrNull(1200) {
            repeat(1000) {
                println("job i am sleeping $it ...")
                delay(500)
            }
            "Done"
        } ?: "Jack"
        println("result is $result")
    }
}