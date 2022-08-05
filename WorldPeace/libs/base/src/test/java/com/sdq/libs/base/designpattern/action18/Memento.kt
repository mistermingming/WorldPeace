package com.sdq.libs.base.designpattern.action18

import org.junit.Test
import java.util.*

/**
 *
 * @PackageName com.sdq.libs.base.designpattern.action18
 * @date 2022/8/5 15:47
 * @author songdongqi
 */
/**
 * 备忘录模式
 */
class Memento {
    @Test
    fun execute() {
        val text = StringBuilder()
        text.replace(0, text.length, "2222")
        println("$text")
    }
}

class InputText {
    private val text = StringBuilder()
    fun getText(): String {
        return text.toString()
    }

    fun append(input: String) {
        text.append(input)
    }

    fun setText(str: String) {
        text.replace(0, text.length, str)
    }
}

class SnapshotHolder{
    private val snapshots = Stack<InputText>()


}