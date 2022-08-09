package com.sdq.libs.base.serialize

import org.junit.Test
import java.io.*

/**
 * ~ 为什么我们要序列化?
 * 因为我们需要将内存中的对象存储到媒介中，或者我们需要将一个对象通过网络传输到另外一个系统中。
 * ~ 序列化的机制
 * 序列化最终的目的是为了对象可以跨平台存储和进行网络传输，而我们进行跨平台存储和网络传输的方式就是 IO，
 * 而 IO 支持的数据格式就是字节数组。
 * 将对象转成字节数组的时候需要制定一种规则，这种规则就是序列化机制。
 * 序列化就是将一个个角色对象和装备对象存储到硬盘，然后留下一张原来对象的结构图纸，
 * 反序列化就是将硬盘里一个个对象读出来照着图纸逐个还原恢复。
 * ~ 常见序列化的方式
 * (1)JDK 原生
 * (2)ProtoBuf
 * (3)Hessian
 * (4)Kryo
 * (5)JSON
 * JSON 序列化常见的框架有：fastJSON、Jackson、Gson 等。
 * ~ 序列化技术的选型
 * 选型最重要的就是要考虑这三个方面：协议是否支持跨平台、序列化的速度、序列化生成的体积。
 */
class Synthesis {
    @Test
    fun jdk() {
        val fos = FileOutputStream("D:\\temp.txt")
        val oos = ObjectOutputStream(fos)
        val user = User(2, "xx")
        oos.writeObject(user)
        oos.flush()
        oos.close()

        val fis = FileInputStream("D:\\temp.txt")
        val oin = ObjectInputStream(fis)
        val userRead = oin.readObject() as User
        println("name = ${userRead.name}")
    }
}

data class User(
    val age: Int,
    val name: String
) : Serializable