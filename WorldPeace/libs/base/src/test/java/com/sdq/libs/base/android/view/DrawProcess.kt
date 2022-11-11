package com.sdq.libs.base.android.view

/**
 *
 * @PackageName com.sdq.libs.base.android.view
 * @date 2022/11/11 15:10
 * @author songdongqi
 */
class DrawProcess {
    /**
     * ViewRootImpl.java
     * doTraversal()->performTraversals()->performDraw()->draw(fullRedrawNeeded);
     * //软件渲染  drawSoftware()
     * 通过mSurface.lockCanvas获取Canvas
     * 通过draw方法，将根View及其子View遍历绘制到Canvas上
     * 通过surface.unlockCanvasAndPost将绘制内容提交给surfaceFlinger进行合成
     * SurfaceFlinger作为消费者处理图形后，我们的界面就显示出来了。
     */
}