package com.sdq.libs.base.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class Principle {
    private suspend fun a() {
        println("aa")
    }
    @Test
    fun coroutinesCode(): Unit = runBlocking {
        launch {
            a()
            println("")
        }
    }
}