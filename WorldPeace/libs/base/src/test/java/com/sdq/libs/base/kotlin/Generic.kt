package com.sdq.libs.base.kotlin

/**
 *
 * @PackageName com.sdq.libs.base.kotlin
 * @date 2022/11/30 13:32
 * @author songdongqi
 */
/**
 * Consumer in ,Producer out
 * 两种使用方式：
 * 第一种：在使用处声明；
 * 第二种：在类或接口的定义处声明。
 */

//能读不能写,能用父类型去获取数据，不确定具体类型，不能传
//对象只作为返回值传出
//out == java extends
interface IProducer<out T> {
    val entity: T
    fun produce(): T
    fun doSomeThing(entity: @UnsafeVariance T)
}

//能写不能读，能传入子类型，不确定具体类型，不能读，但可以用Object读
//对象只作为参数传入
//in == java super
interface IConsumer<in T> {
    fun comsume(t: T)
    fun output(): @UnsafeVariance T
}

abstract class Complex<in I, out O> {
    abstract val outEntityFirst: O
    private var outEntitySecond: O? = null //private 没有getter
    abstract fun produce(): O
    abstract fun comsume(i: I)
    abstract fun doSomeThing(entity: @UnsafeVariance O)
    abstract fun output(): @UnsafeVariance I
}

open class Entity

class Article : Entity()

class Generic {
    @org.junit.Test
    fun test() {
        val entityArticle = object : IProducer<Article> {
            override val entity: Article
                get() = TODO("Not yet implemented")

            override fun produce(): Article {
                TODO("Not yet implemented")
            }

            override fun doSomeThing(entity: Article) {
                TODO("Not yet implemented")
            }
        }

        val entityProducer = object : IProducer<Entity> {
            override val entity: Entity
                get() = Article()

            override fun produce(): Entity = Entity()

            override fun doSomeThing(entity: Entity) {
                TODO("Not yet implemented")
            }
        }

        val entityConsumer = object : IConsumer<Article> {
            override fun comsume(t: Article) {
                TODO("Not yet implemented")
            }

            override fun output(): Article {
                TODO("Not yet implemented")
            }
        }

        val entity = entityProducer.produce()
        entityProducer.doSomeThing(Article())

        val complex = object : Complex<Entity, Article>() {
            override val outEntityFirst: Article
                get() = Article()

            override fun produce(): Article = Article()

            override fun comsume(i: Entity) {
                TODO("Not yet implemented")
            }

            override fun doSomeThing(entity: Article) {
                TODO("Not yet implemented")
            }

            override fun output(): Entity {
                TODO("Not yet implemented")
            }
        }
    }
}

//====================================================================
/**
 * 在使用处声明；
 */
internal abstract class Animal {
    fun eat() {
        println("我是" + myName() + ", 我最喜欢吃" + myFavoriteFood())
    }

    abstract fun myName(): String
    abstract fun myFavoriteFood(): String
}

internal class Fish : Animal() {
    override fun myName(): String {
        return "鱼"
    }

    override fun myFavoriteFood(): String {
        return "虾米"
    }
}

internal class Cat : Animal() {
    override fun myName(): String {
        return "猫"
    }

    override fun myFavoriteFood(): String {
        return "小鱼干"
    }
}

fun extendsFun() {

    val fishList: MutableList<Fish> = ArrayList()

    fishList.add(Fish())

    val catList: MutableList<Cat> = ArrayList()

    catList.add(Cat())

    val animals1: MutableList<out Animal> = fishList

    val animals2: MutableList<out Animal> = catList

    // 协变就好比有多个集合，每个集合存储的是某中特定动物（extends Animal），但是不告诉你那个集合里存储的是鱼，
    // 哪个是猫。所以你虽然可以从任意一个集合中读取一个动物信息，没有问题，但是你没办法将一条鱼的信息存储到鱼
    // 的集合里，因为仅从变量 animals1、animals2 的类型声明上来看你不知道哪个集合里存储的是鱼，哪个集合里是
    // 猫。 假如报错的代码不报错了，那不就说明把一条鱼塞进了一堆猫里，这属于给猫加菜啊，所以肯定是不行的。
    // ? extends 类型通配符所表达的协变就是这个意思。
//    animals2.add(Fish()) // 报错

    val animal1 = animals1[0]

    val animal2 = animals2[0]

    animal1.eat()

    animal2.eat()

}

fun superFun() {

    val fishList: MutableList<Fish> = ArrayList()

    fishList.add(Fish())

    val animalList: MutableList<Animal> = ArrayList()

    animalList.add(Cat())

    animalList.add(Fish())

    val fish1: MutableList<in Fish> = fishList

    val fish2: MutableList<in Fish> = animalList

    fish1.add(Fish())

    // 从变量 fish1、fish2 的类型声明上只能知道里面存储的都是鱼的父类，如果这里也不报错的话可就从 fish2 的集
    // 合里拿出一只猫赋值给一条鱼了，这属于谋杀亲鱼。所以肯定也是不行。? super 类型通配符所表达的逆变就是这个意思。
//    val fish: Fish = fish2[0] //报错

}