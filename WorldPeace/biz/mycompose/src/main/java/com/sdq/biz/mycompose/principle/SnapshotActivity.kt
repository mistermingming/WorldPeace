package com.sdq.biz.mycompose.principle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.sdq.biz.mycompose.ui.theme.WorldPeaceTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 *
 * @PackageName com.sdq.biz.mycompose.principle
 * @date 2022/11/17 10:36
 * @author songdongqi
 */
class Dog {
    var name: MutableState<String> = mutableStateOf("")
}

internal class Cat {
    var name: MutableState<String> =
        mutableStateOf("", policy = object : SnapshotMutationPolicy<String> {
            override fun equivalent(a: String, b: String): Boolean = a == b

            override fun merge(previous: String, current: String, applied: String): String =
                "$applied, briefly known as $current, originally known as $previous"
        })
}

class SnapshotActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewComposable()
        }
    }

    @Preview
    @Composable
    fun PreviewComposable() {
        WorldPeaceTheme {
            Demo()
        }
    }

    @Composable
    fun Demo() {
        lifecycleScope.launch {
            delay(100)
            val dog = Dog()
            val text= dog.name
            dog.name.value = "Compose"
        }
        thread {
            val dog = Dog()
            val text= dog.name
            dog.name.value = "Compose"
        }
        val darkMode = mutableStateOf("hello")
        Text(darkMode.value)
        darkMode.value = "Compose"
    }

    /**
     * 创建快照
     * // Output:
     * Fido
     * Spot
     * Fido
     */
    private fun createSnapshot() {
        val dog = Dog()
        dog.name.value = "Spot"
        //"拍摄"程序中所有 State 值的快照，无论它们是在何处创建的
        val snapshot = Snapshot.takeSnapshot()
        dog.name.value = "Fido"

        println(dog.name.value)
        snapshot.enter {
            println(dog.name.value)
        }
        println(dog.name.value)
    }

    private fun snapshot() {
        val dog = Dog()
        dog.name.value = "Spot"

        //创建一个可变快照应
        val snapshot = Snapshot.takeMutableSnapshot()
        println(dog.name.value)
        snapshot.enter {
            dog.name.value = "Fido"
            println(dog.name.value)
        }
        println(dog.name.value)
    }

    /**
     * apply() 之后, enter方法生效
     */
    private fun snapshot01() {
        val dog = Dog()
        dog.name.value = "Spot"

        val snapshot = Snapshot.takeMutableSnapshot()
        println(dog.name.value)
        snapshot.enter {
            dog.name.value = "Fido"
            println(dog.name.value)
        }
        println(dog.name.value)
        snapshot.apply()
        println(dog.name.value)
    }

    private fun snapshot02() {
        val dog = Dog()
        dog.name.value = "Spot"

        Snapshot.withMutableSnapshot {
            println(dog.name.value)
            dog.name.value = "Fido"
            println(dog.name.value)
        }
        println(dog.name.value)
    }

    /**
     * // Output:
     * Spot
     * in snapshot1: Fido
     * Spot
     * in snapshot2: Fluffy
     * before applying: Spot
     * after applying 1: Fido
     * after applying 2: Fido
     */
    private fun snapshot03() {
        val dog = Dog()
        dog.name.value = "Spot"

        val snapshot1 = Snapshot.takeMutableSnapshot()
        val snapshot2 = Snapshot.takeMutableSnapshot()

        println(dog.name.value)
        snapshot1.enter {
            dog.name.value = "Fido"
            println("in snapshot1: " + dog.name.value)
        }
        // Don’t apply it yet, let’s try setting a third value first.

        println(dog.name.value)
        snapshot2.enter {
            dog.name.value = "Fluffy"
            println("in snapshot2: " + dog.name.value)
        }

        // Ok now we can apply both.
        println("before applying: " + dog.name.value)
        snapshot1.apply()
        println("after applying 1: " + dog.name.value)
        snapshot2.apply()
        println("after applying 2: " + dog.name.value)
    }
}