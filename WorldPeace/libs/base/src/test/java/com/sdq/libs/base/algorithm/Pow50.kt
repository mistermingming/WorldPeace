package com.sdq.libs.base.algorithm

import kotlin.math.pow

class Pow50 {
    fun myPow(x: Double, n: Int): Double {
        return x.pow(n.toDouble())
    }

    fun myPow01(x: Double, n: Int): Double {
        return if(n>0){
            quickPow(x,n)
        } else {
            1.0/quickPow(x,-n)
        }
    }

    private fun quickPow(x:Double,n:Int):Double{
        if(n == 0){
            return 1.0
        }
        val y = quickPow(x,n/2)
        return if(n%2==0) y*y else y*y*x
    }
}