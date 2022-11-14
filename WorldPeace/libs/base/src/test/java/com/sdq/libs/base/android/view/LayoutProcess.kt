package com.sdq.libs.base.android.view

/**
 *
 * @PackageName com.sdq.libs.base.android.view
 * @date 2022/8/25 15:17
 * @author songdongqi
 */
class LayoutProcess {
    /**
     * 页面绘制流程之前的准备流程
     * onResume之后才开始 requestLayout
     *
     * ActivityThread.java
     * handleResumeActivity->
     * (1)performResumeActivity -> r.activity.performResume(r.startsNotResumed, reason); ->
     *
     * Activity.java
     * performResume ->mInstrumentation.callActivityOnResume(this);
     *
     * Instrumentation.java
     * activity.onResume();
     *
     * (2)View decor = r.window.getDecorView();
     * wm.addView(decor, l);
     *
     * WindowManagerGlobal.java
     * root.setView(view, wparams, panelParentView, userId);
     *
     * ViewRootImpl.java
     * setView -> requestLayout(); -> scheduleTraversals();
     *

     */


    /**
     * 为什么不能在子线程更新UI
     * ViewRootImpl.checkThread——if (mThread != Thread.currentThread())
     */


}