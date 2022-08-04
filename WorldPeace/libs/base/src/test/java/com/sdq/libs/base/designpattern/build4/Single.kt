package com.sdq.libs.base.designpattern.build4

/**
 *
 * @PackageName com.sdq.libs.base.dm.build7
 * @date 2022/7/20 10:01
 * @author songdongqi
 */
fun out() {

}

object Single01 {
    var a = 0
    fun inside() {
        a = 1
    }
}

class Single private constructor() {
    companion object {
        fun getInstance(): Single {
            return Holder.single
        }
    }

    private object Holder {
        val single = Single()
    }
}