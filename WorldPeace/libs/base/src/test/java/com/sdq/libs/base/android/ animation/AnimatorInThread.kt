package com.sdq.libs.base.android.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Handler
import android.os.HandlerThread
import android.view.ViewPropertyAnimator
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 *
 * @PackageName com.sdq.libs.base.android. animation
 * @date 2022/8/26 14:10
 * @author songdongqi
 */
class AsynAnimUtil private constructor() : LifecycleObserver {

    private var mHandlerThread: HandlerThread? = HandlerThread("anim_run_in_thread")

    private var mHandler: Handler? = mHandlerThread?.run {
        start()
        Handler(this.looper)
    }

    private var mOwner: LifecycleOwner? = null
    private var mAnim: ViewPropertyAnimator? = null

    companion object {
        val instance: AsynAnimUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AsynAnimUtil()
        }
    }

    //启动动画
    fun startAnim(owner: LifecycleOwner?, animator: ViewPropertyAnimator) {
        try {
            if (mOwner != owner) {
                mOwner = owner
                addLoopLifecycleObserver()
            }

            if (mHandlerThread?.isAlive != true) {
                mHandlerThread = HandlerThread("anim_run_in_thread")
                mHandler = mHandlerThread?.run {
                    start()
                    Handler(this.looper)
                }
            }

            mHandler?.post {
                mAnim = animator.setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        destory()
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        super.onAnimationCancel(animation)
                        destory()
                    }

                    override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                        super.onAnimationEnd(animation, isReverse)
                        destory()
                    }
                })
                mAnim?.start()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    // 绑定当前页面生命周期
    private fun addLoopLifecycleObserver() {
        mOwner?.lifecycle?.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        mAnim?.cancel()
        destory()
    }

    private fun destory() {
        try {
            mHandlerThread?.quitSafely()

            mAnim = null
            mOwner = null
            mHandler = null
            mHandlerThread = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}