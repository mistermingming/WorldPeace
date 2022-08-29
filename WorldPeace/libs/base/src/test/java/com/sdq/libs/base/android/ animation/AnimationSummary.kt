package com.sdq.libs.base.android.animation

/**
 *
 * @PackageName com.sdq.libs.base.android. animation
 * @date 2022/8/26 9:22
 * @author songdongqi
 */
class AnimationSummary {
    /**
     * 逐帧动画【Frame Animation】，即顺序播放事先准备的图片。
     * 补间动画【Tween Animation】，View的动画效果可以实现简单的平移、缩放、旋转。
     * 属性动画【Property Animation】，补间动画增强版，支持对对象执行动画。
     * 过渡动画【Transition Animation】,实现Activity或View过渡动画效果。包括5.0之后的MD过渡动画等。
     */

    /**
     * Android动画实现方式分类都可以分为xml定义和java定义。
     * Android 3.0之前版本，逐帧动画，补间动画
     * Android 3.0之后版本，属性动画
     * Android 4.4中，过渡动画 Android 5.0以上 MD的动画效果。
     */

    /**
     * 补间动画 Animation
     * view.setAnimation
     * Animation.java  add AnimationListener
     */

    /**
     * 属性动画 Animator
     * ObjectAnimator 和 ValueAnimator
     * • ValueAnimator 类是先改变值，然后手动赋值 给对象的属性从而实现动画；是间接对对象属性进行操作；
     * • ObjectAnimator 类是先改变值，然后自动赋值 给对象的属性从而实现动画；是直接对对象属性进行操作；
     *
     */
}