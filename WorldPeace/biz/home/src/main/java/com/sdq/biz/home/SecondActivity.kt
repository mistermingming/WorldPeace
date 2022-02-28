package com.sdq.biz.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("songdongqi","onCreate is invoke!!!")
        setContentView(R.layout.activity_home)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("songdongqi","onSaveInstanceState is invoke!!!")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("songdongqi","onRestoreInstanceState is invoke!!!")
    }

    /**
     * Activity从后台重新回到前台时被调用
     */
    override fun onRestart() {
        super.onRestart()
        Log.d("songdongqi","onRestart is invoke!!!")
    }

    /**
     * Activity创建或者从后台重新回到前台时被调用
     */
    override fun onStart() {
        super.onStart()
        Log.d("songdongqi","onStart is invoke!!!")
    }

    /**
     * Activity创建或者从被覆盖、后台重新回到前台时被调用
     */
    override fun onResume() {
        super.onResume()
        Log.d("songdongqi","onResume is invoke!!!")
    }

    /**
     * Activity被覆盖到下面或者锁屏时被调用
     */
    override fun onPause() {
        super.onPause()
        Log.d("songdongqi","onPause is invoke!!!")
    }

    /**
     * 退出当前Activity或者跳转到新Activity时被调用
     */
    override fun onStop() {
        super.onStop()
        Log.d("songdongqi","onStop is invoke!!!")
    }

    /**
     * 退出当前Activity时被调用,调用之后Activity就结束了
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("songdongqi","onDestroy is invoke!!!")
    }
    private fun simpleFlow5() = flow<Int> {
        for (i in 1..5) {
            delay(100)
            emit(i)
            println("emitting $i ${Thread.currentThread().name}")
        }
    }
}