package com.sdq.libs.base.jvm

import org.junit.Test
import java.util.UUID

/**
 *
 * @PackageName com.sdq.libs.base.jvm
 * @date 2022/11/10 15:12
 * @author songdongqi
 */
class UUID {
    @Test
    fun uuidTest(){
        println(UUID.randomUUID().toString())
        println(UUID.randomUUID().toString())
        println(UUID.randomUUID().toString())
    }
}