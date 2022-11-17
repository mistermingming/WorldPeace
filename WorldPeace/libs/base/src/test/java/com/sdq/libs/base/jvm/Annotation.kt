package com.sdq.libs.base.jvm

import org.junit.Test
import java.lang.annotation.Inherited
import javax.xml.transform.OutputKeys.METHOD

/**
 *
 * @PackageName com.sdq.libs.base.jvm
 * @date 2022/11/16 13:31
 * @author songdongqi
 */

@MustBeDocumented
@Target(AnnotationTarget.TYPE,AnnotationTarget.FUNCTION)
annotation class MyDocument {

}

@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Inherited
@Retention(AnnotationRetention.RUNTIME)
annotation class MyInherited

class Annotation {
    @Test
    fun test(){
        println(A::class.java.getAnnotation(MyInherited::class.java))
        println(B::class.java.getAnnotation(MyInherited::class.java))
        println(C::class.java.getAnnotation(MyInherited::class.java))
    }
}

@MyInherited
open class A

internal open class B : A()

internal class C : B()