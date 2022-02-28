package com.sdq.libs.base

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

/**
 * 协程全局异常处理类，不会变更协程异常流程
 * 用于保存和上报协程异常信息
 */
class GlobalCoroutineExceptionHandler : CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*>
        get() = CoroutineExceptionHandler

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        Log.d("songdongqi","global coroutine exception")
    }
}