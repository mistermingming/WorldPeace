package com.sdq.libs.base.android.view

/**
 *
 * @PackageName com.sdq.libs.base.android.view
 * @date 2022/8/25 15:17
 * @author songdongqi
 */
class LayoutProcess {
    /**
     * 当前进程所有窗口 View，最终都会被存储到WindowManagerGlobal 单例的 mViews 集合中
     */

    /**
     * Activity.java
     * on attach()
     * mWindow = new PhoneWindow(this, window, activityConfigCallback);
     * mWindowManager = mWindow.getWindowManager();
     */

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
     * ViewManager wm = a.getWindowManager();
     * wm.addView(decor, l);
     *
     * WindowManagerGlobal.java
     * root = new ViewRootImpl(view.getContext(), display);
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