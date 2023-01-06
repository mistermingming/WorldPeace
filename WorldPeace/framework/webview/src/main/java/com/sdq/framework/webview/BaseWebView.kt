package com.sdq.framework.webview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 *
 * @PackageName com.sdq.framework.webview
 * @date 2023/1/6 14:20
 * @author songdongqi
 */
/**
 * 初始化
 * Application 中：
 * // 根据手机 CPU 核心数（或者手机内存等条件）设置缓存池容量
 * WebViewPool.getInstance().setMaxPoolSize(min(Runtime.getRuntime().availableProcessors(), 3))
 * WebViewPool.getInstance().init(applicationContext)
 * 获取
 * 在 Activity 中：
 * // 从缓存池获取
 * private val mWebView by lazy { WebViewPool.getInstance().getWebView(this) }
 * // 设置生命周期监听
 * mWebView.setLifecycleOwner(this)
 * // 添加到 RelativeLayout 容器中
 * mBinding.webContainer.addView(
 *      mWebView,
 *      RelativeLayout.LayoutParams(
 *          RelativeLayout.LayoutParams.MATCH_PARENT,
 *          RelativeLayout.LayoutParams.MATCH_PARENT
 *      )
 * )
 */
class BaseWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : WebView(context, attrs), LifecycleEventObserver {

    init {
        // WebView 调试模式开关
        setWebContentsDebuggingEnabled(true)
        // 不显示滚动条
        isVerticalScrollBarEnabled = false
        isHorizontalScrollBarEnabled = false
        // 初始化设置
        WebUtil.defaultSettings(context, this)
    }

    /**
     * 获取当前url
     */
    override fun getUrl(): String? {
        return super.getOriginalUrl() ?: return super.getUrl()
    }

    override fun canGoBack(): Boolean {
        val backForwardList = copyBackForwardList()
        val currentIndex = backForwardList.currentIndex - 1
        if (currentIndex >= 0) {
            val item = backForwardList.getItemAtIndex(currentIndex)
            if (item?.url == "about:blank") {
                return false
            }
        }
        return super.canGoBack()
    }

    /**
     * 设置 WebView 生命管控（自动回调生命周期方法）
     */
    fun setLifecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    /**
     * 生命周期回调
     */
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME -> onResume()
            Lifecycle.Event.ON_STOP -> onPause()
            Lifecycle.Event.ON_DESTROY -> {
                source.lifecycle.removeObserver(this)
                onDestroy()
            }
            else -> {}
        }
    }

    /**
     * 生命周期 onResume()
     */
    @SuppressLint("SetJavaScriptEnabled")
    override fun onResume() {
        super.onResume()
        settings.javaScriptEnabled = true
    }

    /**
     * 生命周期 onPause()
     */
    override fun onPause() {
        super.onPause()
    }

    /**
     * 生命周期 onDestroy()
     * 父类没有 需要自己写
     */
    fun onDestroy() {
        settings.javaScriptEnabled = false

        WebViewPool.getInstance().recycle(this)
    }

    /**
     * 释放资源操作
     */
    fun release() {
        (parent as ViewGroup?)?.removeView(this)
        removeAllViews()
        stopLoading()
        setCustomWebViewClient(null)
        setCustomWebChromeClient(null)
        loadUrl("about:blank")
        clearHistory()
    }

    fun setCustomWebViewClient(client: BaseWebViewClient?) {
        if (client == null) {
            super.setWebViewClient(WebViewClient())
        } else {
            super.setWebViewClient(client)
        }
    }

    fun setCustomWebChromeClient(client: BaseWebChromeClient?) {
        super.setWebChromeClient(client)
    }
}